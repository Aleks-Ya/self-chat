package ru.yaal.seflchat.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;

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
    public void fireDialogAdded(Dialog newDialog) {
        dialogAddedListeners.forEach(listener -> listener.dialogAdded(newDialog));
    }

    @Override
    public void fireDialogRenamed(Dialog newDialog, String oldName) {
        dialogRenamedListeners.forEach(listener -> listener.dialogRenamed(newDialog, oldName));
    }

    @Override
    public void fireDialogRemoved(Dialog dialog) {
        dialogRemovedListeners.forEach(listener -> listener.dialogRemoved(dialog));
    }

    @Override
    public void fireDialogSelected(Dialog newDialog, Dialog oldDialog) {
        dialogSelectedListeners.forEach(listener -> listener.dialogSelected(newDialog, oldDialog));
    }

    @Override
    public void fireDialogMessageAdded(Dialog dialog, Message message) {
        dialogMessageAddedListeners.forEach(listener -> listener.dialogMessageAdded(dialog, message));
    }

    @Override
    public void fireCorrespondenceSelected(Correspondence newCorrespondence) {
        correspondenceSelectedListeners.forEach(listener -> listener.correspondenceSelected(newCorrespondence));
    }
}
