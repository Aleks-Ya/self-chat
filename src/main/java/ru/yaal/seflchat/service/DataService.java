package ru.yaal.seflchat.service;

import com.vaadin.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;
import ru.yaal.seflchat.data.User;
import ru.yaal.seflchat.repository.DialogRepository;

import java.util.List;

import static ru.yaal.seflchat.vaadin.SessionListener.currentUserAttr;

/**
 * @author Yablokov Aleksey
 */
@Service
public class DataService {
    private final DialogRepository repo;
    private static final String currentDialogAttr = "currentDialogAttr";

    @Autowired
    private DataService(DialogRepository repo) {
        this.repo = repo;
    }

    public List<Dialog> getCurrentUserDialogs() {
        User user = getCurrentUser();
        return repo.findByUserId(user.getId());
    }

    private User getCurrentUser() {
        return (User) VaadinSession.getCurrent().getAttribute(currentUserAttr);
    }

    public Dialog createDialogForCurrentUser() {
        Dialog dialog = new Dialog(getCurrentUser().getId());
        repo.insert(dialog);
        return dialog;
    }

    public synchronized Dialog getCurrentDialog() {
        Dialog dialog = (Dialog) VaadinSession.getCurrent().getAttribute(currentDialogAttr);
        if (dialog == null) {
            List<Dialog> dialogs = getCurrentUserDialogs();
            return dialogs.isEmpty() ? createDialogForCurrentUser() : dialogs.get(0);
        }
        return dialog;
    }

    public synchronized void setCurrentDialog(Dialog dialog) {
        VaadinSession.getCurrent().setAttribute(currentDialogAttr, dialog);
    }

    public synchronized Dialog addMessageToCurrentDialog(Message message) {
        return repo.save(getCurrentDialog().withAddMessage(message));
    }
}
