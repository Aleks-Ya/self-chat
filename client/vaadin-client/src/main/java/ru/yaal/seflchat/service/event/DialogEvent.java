package ru.yaal.seflchat.service.event;

import lombok.Getter;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;

import java.util.function.Supplier;

/**
 * @author Aleksey Yablokov
 */
@Getter
public class DialogEvent extends Event {
    private Dialog changedDialog;

    public DialogEvent(Dialog changedDialog, Supplier<Correspondence> selectedCorrespondence, Supplier<Dialog> selectedDialog) {
        super(selectedCorrespondence, selectedDialog);
        this.changedDialog = changedDialog;
    }
}
