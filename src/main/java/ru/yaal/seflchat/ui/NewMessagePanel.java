package ru.yaal.seflchat.ui;

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

        area.addValueChangeListener(event -> {
            String content = (String) event.getProperty().getValue();
            if (!content.isEmpty()) {
                MessageImpl message = new MessageImpl(content);
                dialog.addMessage(message);
                messagesPanel.addMessage(message);
                area.clear();
            }
        });
        addComponent(area);
        setSizeFull();
    }
}
