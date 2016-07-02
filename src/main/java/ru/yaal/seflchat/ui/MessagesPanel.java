package ru.yaal.seflchat.ui;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
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
//        vertical.addComponentAttachListener(event -> markAsDirty());
        setScrollTop(9999);
        service.addListener(this);
        setContent(vertical);
        setSizeFull();
    }

    private void addMessage(Message message) {
        Label label = new Label();
        label.setContentMode(ContentMode.HTML);
        label.setReadOnly(true);
        label.setWidth(70, Unit.PERCENTAGE);
        vertical.addComponent(label);
        if (message.getAlignment() == Message.Alignment.RIGHT) {
            label.setValue("<p align='right'>" + message.getContent() + "</p>");
            vertical.setComponentAlignment(label, Alignment.TOP_RIGHT);
        } else {
            label.setValue("<p align='left'>" + message.getContent() + "</p>");
            vertical.setComponentAlignment(label, Alignment.TOP_LEFT);
        }
    }

    @Override
    public void dialogChanged(Dialog dialog) {
        vertical.removeAllComponents();
        service.getCurrentDialog().getMessages().forEach(this::addMessage);
    }
}
