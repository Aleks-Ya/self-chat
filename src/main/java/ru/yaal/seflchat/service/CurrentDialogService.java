package ru.yaal.seflchat.service;

import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public interface CurrentDialogService {

    List<Dialog> getCurrentUserDialogs();

    Dialog getCurrentDialog();

    Dialog addMessageToCurrentDialog(Message message);

    void addListener(DialogListener listener);

    void setCurrentDialog(Dialog dialog);

    Message.Alignment getNextMessageAlignment();

    void clearCurrentDialog();

    interface DialogListener {
        void dialogChanged(Dialog dialog);
    }
}
