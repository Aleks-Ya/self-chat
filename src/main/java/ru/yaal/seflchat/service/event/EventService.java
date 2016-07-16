package ru.yaal.seflchat.service.event;

import ru.yaal.seflchat.data.Dialog;

/**
 * @author Aleksey Yablokov
 */
public interface EventService {
    void fireDialogAdded(Dialog dialog);

    void fireDialogRenamed(Dialog newDialog);

    void fireDialogRemoved(Dialog dialog);

    void fireDialogSelected(Dialog dialog);

    void fireDialogMessageAdded(Dialog dialog);

    void fireDialogCleared(Dialog dialog);

    void fireCorrespondenceSelected();

    interface DialogAddedListener {
        void dialogAdded(DialogEvent event);
    }

    interface DialogRenamedListener {
        void dialogRenamed(DialogEvent event);
    }

    interface DialogRemovedListener {
        void dialogRemoved(DialogEvent event);
    }

    interface DialogClearedListener {
        void dialogCleared(DialogEvent event);
    }

    interface DialogSelectedListener {
        void dialogSelected(DialogEvent event);
    }

    interface DialogMessageAddedListener {
        void dialogMessageAdded(DialogEvent event);
    }

    interface CorrespondenceSelectedListener {
        void correspondenceSelected(Event event);
    }
}
