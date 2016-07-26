package ru.yaal.seflchat.ui.center;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
@Scope("prototype")
class DialogActionsPanel extends Panel {

    @Autowired
    DialogActionsPanel(CorrespondenceService corService, DialogNamePanel dialogNamePanel) {
        log.info("Create " + getClass().getSimpleName());

        Button bRename = new Button("Rename", event -> dialogNamePanel.edit());
        Button bClear = new Button("Clear", event -> corService.clearCurrentDialog());
        Button bDelete = new Button("Delete", event -> corService.removeDialog(corService.getCurrentDialog()));

        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(bRename);
        layout.addComponent(bClear);
        layout.addComponent(bDelete);
        setContent(layout);
        setSizeFull();
    }
}
