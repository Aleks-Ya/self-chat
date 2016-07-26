package ru.yaal.seflchat.ui.center;

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
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;

import static com.vaadin.event.ShortcutAction.ModifierKey.ALT;
import static com.vaadin.event.ShortcutAction.ModifierKey.CTRL;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
@UIScope
public class NewMessagePanel extends VerticalLayout {
    private final TextArea area = new TextArea("Enter message and press Ctrl-Enter, Ctrl-Alt-R or Ctrl-Alt-L:");
    private final CorrespondenceService service;

    @Autowired
    NewMessagePanel(CorrespondenceService service) {
        this.service = service;

        log.info("Create " + getClass().getSimpleName());
        area.setRows(5);
        area.setColumns(50);

        area.addShortcutListener(new ShortcutListener("Ctrl-Enter",
                ShortcutAction.KeyCode.ENTER, new int[]{CTRL}) {
            @Override
            public void handleAction(Object sender, Object target) {
                Message.Alignment alignment = service.getNextMessageAlignment();
                handler(target, alignment);
            }
        });
        area.addShortcutListener(new ShortcutListener("Ctrl-Alt-R",
                ShortcutAction.KeyCode.R, new int[]{CTRL, ALT}) {
            @Override
            public void handleAction(Object sender, Object target) {
                handler(target, Message.Alignment.RIGHT);
            }
        });
        area.addShortcutListener(new ShortcutListener("Ctrl-Alt-L",
                ShortcutAction.KeyCode.L, new int[]{CTRL, ALT}) {
            @Override
            public void handleAction(Object sender, Object target) {
                handler(target, Message.Alignment.LEFT);
            }
        });

        addComponent(area);
        setComponentAlignment(area, Alignment.MIDDLE_CENTER);
        setSizeFull();
        area.focus();
    }

    private void handler(Object target, Message.Alignment alignment) {
        String content = ((TextArea) target).getValue();
        if (!content.isEmpty()) {
            Message message = new Message(content, alignment);
            service.addMessageToCurrentDialog(message);
            area.clear();
        }
    }
}
