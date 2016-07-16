package ru.yaal.seflchat.service.event;

import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;

/**
 * @author Aleksey Yablokov
 */
public interface EventService {
    void fireDialogAdded(Dialog newDialog);

    void fireDialogRenamed(Dialog newDialog, String oldName);

    void fireDialogRemoved(Dialog dialog);

    void fireDialogSelected(Dialog newDialog, Dialog oldDialog);

    void fireDialogMessageAdded(Dialog dialog, Message message);

    void fireCorrespondenceSelected(Correspondence newCorrespondence);

    interface DialogAddedListener {
        void dialogAdded(Dialog newDialog);
    }

    interface DialogRenamedListener {
        void dialogRenamed(Dialog newDialog, String oldName);
    }

    interface DialogRemovedListener {
        void dialogRemoved(Dialog dialog);
    }

    interface DialogSelectedListener {
        void dialogSelected(Dialog newDialog, Dialog oldDialog);
    }

    interface DialogMessageAddedListener {
        void dialogMessageAdded(Dialog dialog, Message message);
    }

    interface CorrespondenceSelectedListener {
        void correspondenceSelected(Correspondence newCorrespondence);
    }
}
