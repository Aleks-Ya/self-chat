package ru.yaal.seflchat.service;

import com.vaadin.data.Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;
import ru.yaal.seflchat.repository.DialogRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Service
class CurrentDialogServiceImpl implements CurrentDialogService {
    private final DialogRepository repo;
    private final CorrespondenceService correspondenceService;
    private final VaadinService vaadinService;
    private final List<Property<Dialog>> listeners = new ArrayList<>();

    @Autowired
    private CurrentDialogServiceImpl(DialogRepository repo, CorrespondenceService correspondenceService,
                                     VaadinService vaadinService) {
        this.repo = repo;
        this.correspondenceService = correspondenceService;
        this.vaadinService = vaadinService;
    }

    public List<Dialog> getCurrentUserDialogs() {
        return correspondenceService.getCurrentCorrespondence().getUserDialogs();
    }

    private Dialog createDialogForCurrentUser() {
        Dialog dialog = new Dialog("dialog_" + LocalDateTime.now());
        repo.insert(dialog);
        setCurrentDialog(dialog);
        correspondenceService.addDialog(dialog);
        return dialog;
    }

    private void eventListeners(Dialog dialog) {
        listeners.forEach(listener -> listener.setValue(dialog));
    }

    public synchronized Dialog getCurrentDialog() {
        Dialog dialog = vaadinService.getDialogFromSession();
        if (dialog == null) {
            List<Dialog> dialogs = getCurrentUserDialogs();
            if (dialogs.isEmpty()) {
                dialog = createDialogForCurrentUser();
            } else {
                dialog = dialogs.get(0);
            }
            setCurrentDialog(dialog);
        }
        return dialog;
    }

    public synchronized void setCurrentDialog(Dialog dialog) {
        vaadinService.setDialogToSession(dialog);
        fireCurrentDialogChanged();
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
        repo.save(current);
        fireCurrentDialogChanged();
    }

    @Override
    public void fireCurrentDialogChanged() {
        eventListeners(getCurrentDialog());
    }

    @Override
    public synchronized void addMessageToCurrentDialog(Message message) {
        Dialog current = getCurrentDialog();
        current.getMessages().add(message);
        repo.save(current);
        fireCurrentDialogChanged();
    }

    @Override
    public synchronized void renameCurrentDialog(String newName) {
        Dialog current = getCurrentDialog();
        log.info("Rename dialog: \"" + current.getName() + "\" to \"" + newName + "\".");
        current.setName(newName);
        repo.save(current);
        fireCurrentDialogChanged();
    }

    @Override
    public void addListener(Property<Dialog> listener) {
        listeners.add(listener);
    }
}
