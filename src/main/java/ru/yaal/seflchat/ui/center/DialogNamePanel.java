package ru.yaal.seflchat.ui.center;

import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;
import ru.yaal.seflchat.service.event.DialogEvent;
import ru.yaal.seflchat.service.event.EventService;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
@UIScope
class DialogNamePanel extends Panel implements EventService.DialogSelectedListener {
    private final TextField name = new TextField("Dialog name");

    @Autowired
    public DialogNamePanel(CorrespondenceService corService) {
        name.setWidth("100%");
        name.addValueChangeListener(event -> corService.renameCurrentDialog(event.getProperty().getValue().toString()));

        setContent(name);
    }

    @Override
    public void dialogSelected(DialogEvent event) {
        name.setValue(event.getSelectedDialog().get().getName());
    }
}
