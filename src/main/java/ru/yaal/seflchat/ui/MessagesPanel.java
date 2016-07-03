package ru.yaal.seflchat.ui;

import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;
import ru.yaal.seflchat.service.CurrentDialogService;

/**
 * @author Yablokov Aleksey
 */
@Component
@Slf4j
class MessagesPanel extends Panel implements CurrentDialogService.DialogListener {
    private final VerticalLayout vertical = new VerticalLayout();
    private CurrentDialogService service;

    @Autowired
    MessagesPanel(CurrentDialogService service) {
        this.service = service;
        log.info("Create " + getClass().getSimpleName());
        setScrollTop(9999);
        service.addListener(this);
        setContent(vertical);
        setSizeFull();
    }

    private void addMessage(Message message) {
        MessagePanel panel = new MessagePanel();
        panel.setValue(message);
        vertical.addComponent(panel);
    }

    @Override
    public void dialogChanged(Dialog dialog) {
        vertical.removeAllComponents();
        service.getCurrentDialog().getMessages().forEach(this::addMessage);
    }
}
