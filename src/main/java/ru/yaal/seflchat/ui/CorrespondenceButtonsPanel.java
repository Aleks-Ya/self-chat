package ru.yaal.seflchat.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
class CorrespondenceButtonsPanel extends Panel {

    @Autowired
    CorrespondenceButtonsPanel(CorrespondenceService correspondenceService) {
        log.info("Create " + getClass().getSimpleName());

        Button bAdd = new Button("Add dialog", event -> correspondenceService.addDialog(new Dialog("new_dialog")));
        Button bRemove = new Button("Remove dialog");

        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(bAdd);
        layout.addComponent(bRemove);
        setContent(layout);
        setSizeFull();
    }
}
