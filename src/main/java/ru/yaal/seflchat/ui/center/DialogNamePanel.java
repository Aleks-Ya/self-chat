package ru.yaal.seflchat.ui.center;

import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
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
class DialogNamePanel extends Panel implements EventService.DialogSelectedListener, EventService.DialogRenamedListener {
    private final TextField edit = new TextField();
    private final Label view = new Label();

    @Autowired
    public DialogNamePanel(CorrespondenceService corService) {
        view.setWidth("100%");

        edit.setWidth("100%");
        edit.addValueChangeListener(event -> {
            corService.renameCurrentDialog(event.getProperty().getValue().toString());
            view();
        });
        edit.addBlurListener(event -> {
            corService.renameCurrentDialog(((TextField) event.getSource()).getValue());
            view();
        });

        view();
    }

    void edit() {
        edit.focus();
        setContent(edit);
    }

    private void view() {
        setContent(view);
    }

    @Override
    public void dialogSelected(DialogEvent event) {
        String name = event.getSelectedDialog().get().getName();
        update(name);
    }

    private void update(String dialogName) {
        edit.setValue(dialogName);
        view.setValue(dialogName);
    }

    @Override
    public void dialogRenamed(DialogEvent event) {
        update(event.getSelectedDialog().get().getName());
    }
}
