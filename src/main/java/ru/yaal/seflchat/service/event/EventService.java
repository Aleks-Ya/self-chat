package ru.yaal.seflchat.service.event;

/**
 * @author Aleksey Yablokov
 */
public interface EventService {
    void fireDialogAdded(DialogEvent event);

    void fireDialogRenamed(DialogEvent event);

    void fireDialogRemoved(DialogEvent event);

    void fireDialogSelected(DialogEvent event);

    void fireDialogMessageAdded(DialogEvent event);

    void fireCorrespondenceSelected(Event event);

    interface DialogAddedListener {
        void dialogAdded(DialogEvent event);
    }

    interface DialogRenamedListener {
        void dialogRenamed(DialogEvent event);
    }

    interface DialogRemovedListener {
        void dialogRemoved(DialogEvent event);
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
