package ru.yaal.seflchat.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Message;
import ru.yaal.seflchat.service.dialog.CurrentDialogService;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
@UIScope
class NewMessagePanel extends VerticalLayout {

    @Autowired
    NewMessagePanel(CurrentDialogService service) {
        log.info("Create " + getClass().getSimpleName());
        TextArea area = new TextArea("Enter message:");
        area.setRows(5);
        area.setColumns(50);

        ShortcutListener ctrlEnterListener = new ShortcutListener("Ctrl-Enter",
                ShortcutAction.KeyCode.ENTER, new int[]{ShortcutAction.ModifierKey.CTRL}) {
            @Override
            public void handleAction(Object sender, Object target) {
                String content = ((TextArea) target).getValue();
                if (!content.isEmpty()) {
                    Message.Alignment alignment = service.getNextMessageAlignment();
                    Message message = new Message(content, alignment);
                    service.addMessageToCurrentDialog(message);
                    area.clear();
                }
            }
        };
        area.addShortcutListener(ctrlEnterListener);

        addComponent(area);
        setComponentAlignment(area, Alignment.MIDDLE_CENTER);
        setSizeFull();
        area.focus();
    }
}
