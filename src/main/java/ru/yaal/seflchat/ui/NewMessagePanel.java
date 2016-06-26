package ru.yaal.seflchat.ui;

import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.MessageImpl;

/**
 * @author Yablokov Aleksey
 */
public class NewMessagePanel extends VerticalLayout {

    public NewMessagePanel(Dialog dialog, MessagePanel messagePanel) {

        TextArea tfEnterMessage = new TextArea("Enter message:");
        tfEnterMessage.setRows(5);
        tfEnterMessage.setColumns(50);

        tfEnterMessage.addValueChangeListener(event -> {
            String content = (String) event.getProperty().getValue();
            if (!content.isEmpty()) {
                MessageImpl message = new MessageImpl(content);
                dialog.addMessage(message);
                messagePanel.addMessage(message);
                tfEnterMessage.clear();
            }
        });
        addComponent(tfEnterMessage);
        setSizeFull();
    }
}
