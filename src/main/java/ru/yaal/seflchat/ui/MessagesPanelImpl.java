package ru.yaal.seflchat.ui;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import ru.yaal.seflchat.data.Message;

/**
 * @author Yablokov Aleksey
 */
class MessagesPanelImpl extends Panel implements MessagesPanel {
    private boolean nextMessageRightAlignment = false;
    private final VerticalLayout vertical = new VerticalLayout();

    MessagesPanelImpl() {
        vertical.addComponentAttachListener(event -> markAsDirty());
        setScrollTop(9999);

        setContent(vertical);
        setSizeFull();
    }

    @Override
    public void addMessage(Message message) {
        Label label = new Label();
        label.setContentMode(ContentMode.HTML);
        label.setReadOnly(true);
        label.setWidth(70, Unit.PERCENTAGE);
        vertical.addComponent(label);
        if (nextMessageRightAlignment) {
            label.setValue("<p align='right'>" + message.getContent() + "</p>");
            vertical.setComponentAlignment(label, Alignment.TOP_RIGHT);
        } else {
            label.setValue("<p align='left'>" + message.getContent() + "</p>");
            vertical.setComponentAlignment(label, Alignment.TOP_LEFT);
        }
        nextMessageRightAlignment = !nextMessageRightAlignment;
    }
}
