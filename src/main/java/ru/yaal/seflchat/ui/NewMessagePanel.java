package ru.yaal.seflchat.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.MessageImpl;

/**
 * @author Yablokov Aleksey
 */
public class NewMessagePanel extends VerticalLayout {

    public NewMessagePanel(Dialog dialog, MessagesPanel messagesPanel) {

        TextArea area = new TextArea("Enter message:");
        area.setRows(5);
        area.setColumns(50);

        ShortcutListener ctrlEnterListener = new ShortcutListener("Ctrl-Enter",
                ShortcutAction.KeyCode.ENTER, new int[]{ShortcutAction.ModifierKey.CTRL}) {
            @Override
            public void handleAction(Object sender, Object target) {
                String content = ((TextArea) target).getValue();
                if (!content.isEmpty()) {
                    MessageImpl message = new MessageImpl(content);
                    dialog.addMessage(message);
                    messagesPanel.addMessage(message);
                    area.clear();
                }
            }
        };
        area.addShortcutListener(ctrlEnterListener);

        addComponent(area);
        setComponentAlignment(area, Alignment.MIDDLE_CENTER);
        setSizeFull();
    }
}
