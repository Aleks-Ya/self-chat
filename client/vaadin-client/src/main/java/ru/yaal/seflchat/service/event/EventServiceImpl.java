package ru.yaal.seflchat.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;

import java.util.Collection;

/**
 * @author Aleksey Yablokov
 */
@Component
class EventServiceImpl implements EventService {
    @Autowired
    @Lazy
    private CorrespondenceService correspondenceService;

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void fireDialogAdded(Dialog dialog) {
        takeBeans(DialogAddedListener.class).forEach(listener -> listener.dialogAdded(makeDialogEvent(dialog)));
    }

    @Override
    public void fireDialogRenamed(Dialog newDialog) {
        takeBeans(DialogRenamedListener.class).forEach(listener -> listener.dialogRenamed(makeDialogEvent(newDialog)));
    }

    @Override
    public void fireDialogRemoved(Dialog dialog) {
        takeBeans(DialogRemovedListener.class).forEach(listener -> listener.dialogRemoved(makeDialogEvent(dialog)));
    }

    @Override
    public void fireDialogSelected(Dialog dialog) {
        takeBeans(DialogSelectedListener.class).forEach(listener -> listener.dialogSelected(makeDialogEvent(dialog)));
    }

    @Override
    public void fireDialogMessageAdded(Dialog dialog) {
        takeBeans(DialogMessageAddedListener.class).forEach(listener -> listener.dialogMessageAdded(makeDialogEvent(dialog)));
    }

    @Override
    public void fireDialogCleared(Dialog dialog) {
        takeBeans(DialogClearedListener.class).forEach(listener -> listener.dialogCleared(makeDialogEvent(dialog)));
    }

    @Override
    public void fireCorrespondenceSelected(Correspondence correspondence) {
        takeBeans(CorrespondenceSelectedListener.class)
                .forEach(listener -> listener.correspondenceSelected(
                        new Event(() -> correspondence, () -> correspondenceService.getCurrentDialog())));
    }

    private <T> Collection<T> takeBeans(Class<T> clazz) {
        return ctx.getBeansOfType(clazz).values();
    }

    private DialogEvent makeDialogEvent(Dialog dialog) {
        return new DialogEvent(dialog, () -> correspondenceService.getCurrentCorrespondence(), () -> correspondenceService.getCurrentDialog());
    }
}
