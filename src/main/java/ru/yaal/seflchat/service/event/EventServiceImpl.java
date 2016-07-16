package ru.yaal.seflchat.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Aleksey Yablokov
 */
@Component
class EventServiceImpl implements EventService {
    @Autowired
    private List<DialogAddedListener> dialogAddedListeners;

    @Autowired
    private List<DialogRemovedListener> dialogRemovedListeners;

    @Autowired
    private List<DialogRenamedListener> dialogRenamedListeners;

    @Autowired
    private List<DialogSelectedListener> dialogSelectedListeners;

    @Autowired
    private List<DialogMessageAddedListener> dialogMessageAddedListeners;

    @Autowired
    private List<CorrespondenceSelectedListener> correspondenceSelectedListeners;

    @Override
    public void fireDialogAdded(DialogEvent event) {
        dialogAddedListeners.forEach(listener -> listener.dialogAdded(event));
    }

    @Override
    public void fireDialogRenamed(DialogEvent event) {
        dialogRenamedListeners.forEach(listener -> listener.dialogRenamed(event));
    }

    @Override
    public void fireDialogRemoved(DialogEvent event) {
        dialogRemovedListeners.forEach(listener -> listener.dialogRemoved(event));
    }

    @Override
    public void fireDialogSelected(DialogEvent event) {
        dialogSelectedListeners.forEach(listener -> listener.dialogSelected(event));
    }

    @Override
    public void fireDialogMessageAdded(DialogEvent event) {
        dialogMessageAddedListeners.forEach(listener -> listener.dialogMessageAdded(event));
    }

    @Override
    public void fireCorrespondenceSelected(Event event) {
        correspondenceSelectedListeners.forEach(listener -> listener.correspondenceSelected(event));
    }
}
