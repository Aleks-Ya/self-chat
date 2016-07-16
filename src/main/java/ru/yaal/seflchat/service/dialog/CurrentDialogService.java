package ru.yaal.seflchat.service.dialog;

import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public interface CurrentDialogService {

    List<Dialog> getCurrentUserDialogs();

    Dialog getCurrentDialog();

    void addMessageToCurrentDialog(Message message);

    void addListener(CurrentDialogListener listener);

    void setCurrentDialog(String dialogId);

    Message.Alignment getNextMessageAlignment();

    void clearCurrentDialog();

    void fireCurrentDialogChanged();

    void renameCurrentDialog(String newName);

    interface CurrentDialogListener {
        void dialogChanged(Dialog dialog);
    }
}
