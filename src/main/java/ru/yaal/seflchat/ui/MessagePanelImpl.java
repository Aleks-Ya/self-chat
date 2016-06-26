package ru.yaal.seflchat.ui;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import ru.yaal.seflchat.data.Message;

/**
 * @author Yablokov Aleksey
 */
public class MessagePanelImpl extends VerticalLayout implements MessagePanel {
    private boolean nextMessageRightAlignment = false;

    public MessagePanelImpl() {
        setSpacing(true);
    }

    @Override
    public void addMessage(Message message) {
        Label label = new Label();
        label.setContentMode(ContentMode.HTML);
        label.setReadOnly(true);
        label.setWidth(70, Unit.PERCENTAGE);
        addComponent(label);
        if (nextMessageRightAlignment) {
            label.setValue("<p align='right'>" + message.getContent() + "</p>");
            setComponentAlignment(label, Alignment.TOP_RIGHT);
        } else {
            label.setValue("<p align='left'>" + message.getContent() + "</p>");
            setComponentAlignment(label, Alignment.TOP_LEFT);
        }
        nextMessageRightAlignment = !nextMessageRightAlignment;
    }
}
