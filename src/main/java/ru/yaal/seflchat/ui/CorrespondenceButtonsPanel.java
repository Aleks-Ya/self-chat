package ru.yaal.seflchat.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
class CorrespondenceButtonsPanel extends Panel {

    CorrespondenceButtonsPanel() {
        log.info("Create " + getClass().getSimpleName());

        Button bAdd = new Button("Add correspondence");
        Button bRemove = new Button("Remove correspondence");

        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(bAdd);
        layout.addComponent(bRemove);
        setContent(layout);
        setSizeFull();
    }
}
