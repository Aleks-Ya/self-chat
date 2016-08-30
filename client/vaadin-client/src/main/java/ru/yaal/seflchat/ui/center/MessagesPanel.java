package ru.yaal.seflchat.ui.center;

import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;
import ru.yaal.seflchat.service.event.DialogEvent;
import ru.yaal.seflchat.service.event.EventService;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
@UIScope
class MessagesPanel extends Panel implements EventService.DialogMessageAddedListener, EventService.DialogSelectedListener {
    private final VerticalLayout vertical = new VerticalLayout();

    @Autowired
    MessagesPanel(CorrespondenceService corService) {
        log.info("Create " + getClass().getSimpleName());
        setScrollTop(9999);
        setContent(vertical);
        setSizeFull();
        update(corService.getCurrentDialog());
    }

    private void addMessage(Message message) {
        MessagePanel panel = new MessagePanel();
        panel.setValue(message);
        vertical.addComponent(panel);
    }

    private void update(Dialog dialog) {
        vertical.removeAllComponents();
        dialog.getMessages().forEach(this::addMessage);
    }

    @Override
    public void dialogMessageAdded(DialogEvent event) {
        update(event.getChangedDialog());
    }

    @Override
    public void dialogSelected(DialogEvent event) {
        update(event.getChangedDialog());
    }
}
