package ru.yaal.seflchat.service.correspondence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;
import ru.yaal.seflchat.repository.CorrespondenceDao;
import ru.yaal.seflchat.repository.DialogDao;
import ru.yaal.seflchat.service.event.EventService;
import ru.yaal.seflchat.service.user.UserService;
import ru.yaal.seflchat.service.vaadin.VaadinService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Service
class CorrespondenceServiceImpl implements CorrespondenceService {
    private final CorrespondenceDao cRepo;
    private final UserService userService;
    private final DialogDao dRepo;
    private final VaadinService vaadinService;
    private final EventService eventService;

    @Autowired
    private CorrespondenceServiceImpl(CorrespondenceDao cRepo, UserService userService,
                                      DialogDao dRepo, VaadinService vaadinService, EventService eventService) {
        this.cRepo = cRepo;
        this.userService = userService;
        this.dRepo = dRepo;
        this.vaadinService = vaadinService;
        this.eventService = eventService;
    }

    public synchronized Correspondence getCurrentCorrespondence() {
        Correspondence correspondence = takeCorrespondenceFromSession();
        if (correspondence == null) {
            correspondence = takeCorrespondenceByUser();
        }
        if (correspondence == null) {
            correspondence = createCorrespondence(new Correspondence(userService.getCurrentUser()));
        }
        setCurrentCorrespondence(correspondence);
        return correspondence;
    }

    private synchronized void setCurrentCorrespondence(Correspondence correspondence) {
        vaadinService.setCorrespondenceIdToSession(correspondence.getId());
        eventService.fireCorrespondenceSelected();
    }

    private Correspondence takeCorrespondenceFromSession() {
        String correspondenceId = vaadinService.getCorrespondenceIdFromSession();
        return correspondenceId != null ? cRepo.findOne(correspondenceId) : null;
    }

    private Correspondence takeCorrespondenceByUser() {
        return cRepo.findByUser(userService.getCurrentUser()).orElse(null);
    }

    @Override
    public Correspondence createCorrespondence(Correspondence correspondence) {
        return cRepo.insert(correspondence);
    }

    @Override
    @Transactional
    public void addDialog(Dialog dialog) {
        dRepo.save(dialog);
        Correspondence correspondence = getCurrentCorrespondence();
        correspondence.getUserDialogs().add(dialog);
        cRepo.save(correspondence);
        eventService.fireDialogAdded(dialog);
    }

    @Override
    public void removeDialog(Dialog dialog) {
        log.info("Delete dialog: {}", dialog);
        dRepo.delete(dialog);
        eventService.fireDialogRemoved(dialog);
    }

    public List<Dialog> getCurrentUserDialogs() {
        return getCurrentCorrespondence().getUserDialogs();
    }

    @Transactional
    private Dialog createDialogForCurrentUser() {
        Dialog dialog = new Dialog("dialog_" + LocalDateTime.now());
        dRepo.insert(dialog);
        setCurrentDialog(dialog.getId());
        addDialog(dialog);
        return dialog;
    }

    public synchronized Dialog getCurrentDialog() {
        Dialog dialog = takeDialogFromSession();
        if (dialog == null) {
            List<Dialog> dialogs = getCurrentUserDialogs();
            if (dialogs.isEmpty()) {
                dialog = createDialogForCurrentUser();
            } else {
                dialog = dialogs.get(0);
            }
            setCurrentDialog(dialog.getId());
        }
        return dialog;
    }

    public synchronized void setCurrentDialog(String dialogId) {
        vaadinService.setDialogIdToSession(dialogId);
        Dialog dialog = dRepo.findOne(dialogId);
        eventService.fireDialogSelected(dialog);
    }

    private Dialog takeDialogFromSession() {
        String dialogId = vaadinService.getDialogIdFromSession();
        return dialogId != null ? dRepo.findOne(dialogId) : null;
    }

    @Override
    public Message.Alignment getNextMessageAlignment() {
        List<Message> messages = getCurrentDialog().getMessages();
        if (messages.isEmpty()) {
            return Message.Alignment.LEFT;
        } else {
            Message.Alignment lastAlignment = messages.get(messages.size() - 1).getAlignment();
            return lastAlignment == Message.Alignment.RIGHT ? Message.Alignment.LEFT : Message.Alignment.RIGHT;
        }
    }

    @Override
    public synchronized void clearCurrentDialog() {
        Dialog current = getCurrentDialog();
        current.getMessages().clear();
        dRepo.save(current);
        eventService.fireDialogCleared(current);
    }

    @Override
    public synchronized void addMessageToCurrentDialog(Message message) {
        Dialog current = getCurrentDialog();
        current.getMessages().add(message);
        dRepo.save(current);
        eventService.fireDialogMessageAdded(current);
    }

    @Override
    public synchronized void renameCurrentDialog(String newName) {
        Dialog current = getCurrentDialog();
        log.info("Rename dialog: \"" + current.getName() + "\" to \"" + newName + "\".");
        current.setName(newName);
        dRepo.save(current);
        eventService.fireDialogRenamed(current);
    }
}
