package ru.yaal.seflchat.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.TextArea;
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
        TextArea area = new TextArea();
        area.setValue(message.getContent());
        area.setReadOnly(true);
        addComponent(area);
        if (nextMessageRightAlignment) {
            setComponentAlignment(area, Alignment.TOP_RIGHT);
        } else {
            setComponentAlignment(area, Alignment.TOP_LEFT);
        }
        nextMessageRightAlignment = !nextMessageRightAlignment;
    }
}
