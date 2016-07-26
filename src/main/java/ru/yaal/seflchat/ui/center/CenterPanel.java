package ru.yaal.seflchat.ui.center;

import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Aleksey Yablokov
 */
@Component
@UIScope
public class CenterPanel extends Panel {

    @Autowired
    public CenterPanel(DialogNamePanel dialogNamePanel, MessagesPanel messagesPanel, NewMessagePanel newMessagePanel) {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(dialogNamePanel);
        layout.addComponent(messagesPanel);
        layout.addComponent(newMessagePanel);
        setContent(layout);
    }
}
