package ru.yaal.seflchat.ui;

import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
class CorrespondencePanel extends Panel {

    @Autowired
    CorrespondencePanel(CorrespondenceListPanel listPanel, CorrespondenceButtonsPanel buttonsPanel) {
        log.info("Create " + getClass().getSimpleName());

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(listPanel);
        layout.addComponent(buttonsPanel);
        setContent(layout);
    }
}
