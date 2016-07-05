package ru.yaal.seflchat.ui;

import com.vaadin.data.Property;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
@UIScope
class MessagesPanel extends Panel implements Property<Dialog> {
    private final VerticalLayout vertical = new VerticalLayout();
    private Dialog value;

    MessagesPanel() {
        log.info("Create " + getClass().getSimpleName());
        setScrollTop(9999);
        setContent(vertical);
        setSizeFull();
    }

    private void addMessage(Message message) {
        MessagePanel panel = new MessagePanel();
        panel.setValue(message);
        vertical.addComponent(panel);
    }

    private void update() {
        vertical.removeAllComponents();
        value.getMessages().forEach(this::addMessage);
    }

    @Override
    public Dialog getValue() {
        return value;
    }

    @Override
    public void setValue(Dialog newValue) throws ReadOnlyException {
        value = newValue;
        update();
    }

    @Override
    public Class<? extends Dialog> getType() {
        return Dialog.class;
    }
}
