package ru.yaal.seflchat.service.correspondence;

import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public interface CorrespondenceService {

    Correspondence getCurrentCorrespondence();

    Correspondence createCorrespondence(Correspondence correspondence);

    void addDialog(Dialog dialog);

    void removeDialog(Dialog dialog);

    List<Dialog> getCurrentUserDialogs();

    Dialog getCurrentDialog();

    void addMessageToCurrentDialog(Message message);

    void setCurrentDialog(String dialogId);

    Message.Alignment getNextMessageAlignment();

    void clearCurrentDialog();

    void renameCurrentDialog(String newName);
}
