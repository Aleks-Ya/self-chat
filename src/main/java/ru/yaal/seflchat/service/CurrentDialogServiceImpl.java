package ru.yaal.seflchat.service;

import com.vaadin.data.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;
import ru.yaal.seflchat.repository.DialogRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Service
class CurrentDialogServiceImpl implements CurrentDialogService {
    private final DialogRepository repo;
    private final CorrespondenceService correspondenceService;
    private final VaadinService vaadinService;
    private static final String currentDialogAttr = "currentDialogAttr";
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
        Dialog dialog = new Dialog();
        repo.insert(dialog);
        setCurrentDialog(dialog);
        correspondenceService.addDialog(dialog);
        return dialog;
    }

    private void eventListeners(Dialog dialog) {
        listeners.forEach(listener -> listener.setValue(dialog));
    }

    public synchronized Dialog getCurrentDialog() {
        Dialog dialog = (Dialog) vaadinService.getCurrentVaadinSession().getAttribute(currentDialogAttr);
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
        vaadinService.getCurrentVaadinSession().setAttribute(currentDialogAttr, dialog);
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
    public void clearCurrentDialog() {
        setCurrentDialog(getCurrentDialog().withClearMessages());
    }

    @Override
    public void fireCurrentDialogChanged() {
        eventListeners(getCurrentDialog());
    }

    public synchronized Dialog addMessageToCurrentDialog(Message message) {
        Dialog dialog = repo.save(getCurrentDialog().withAddMessage(message));
        setCurrentDialog(dialog);
        return dialog;
    }

    @Override
    public void addListener(Property<Dialog> listener) {
        listeners.add(listener);
    }
}
