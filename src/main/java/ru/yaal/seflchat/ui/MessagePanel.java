package ru.yaal.seflchat.ui;

import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Message;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
@UIScope
class MessagePanel extends Panel implements Property<Message> {
    private final VerticalLayout vertical = new VerticalLayout();
    private final Label label = new Label();
    private Message value;

    MessagePanel() {
        log.info("Create " + getClass().getSimpleName());

        label.setContentMode(ContentMode.HTML);
        label.setReadOnly(true);
        label.setWidth(70, Unit.PERCENTAGE);
        vertical.addComponent(label);

        setContent(vertical);
        setSizeFull();
    }

    private void updateData() {
        setText(value.getContent(), value.getAlignment());
    }

    private void setText(String text, Message.Alignment alignment) {
        if (alignment == Message.Alignment.RIGHT) {
            label.setValue("<p align='right'>" + text + "</p>");
            vertical.setComponentAlignment(label, Alignment.TOP_RIGHT);
        } else {
            label.setValue("<p align='left'>" + text + "</p>");
            vertical.setComponentAlignment(label, Alignment.TOP_LEFT);
        }
    }

    @Override
    public Message getValue() {
        return value;
    }

    @Override
    public void setValue(Message newValue) throws ReadOnlyException {
        value = newValue;
        updateData();
    }

    @Override
    public Class<? extends Message> getType() {
        return Message.class;
    }
}
