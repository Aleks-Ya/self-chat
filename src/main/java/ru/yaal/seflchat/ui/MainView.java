package ru.yaal.seflchat.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;
import ru.yaal.seflchat.service.dialog.CurrentDialogService;

/**
 * @author Yablokov Aleksey
 */
@SpringView(name = "")
@Slf4j
class MainView extends VerticalLayout implements View {

    @Autowired
    MainView(NewMessagePanel newMessagePanel, CurrentDialogService service, CorrespondencePanel correspondencePanel,
             CorrespondenceService correspondenceService) {
        log.info("Create " + getClass().getSimpleName());

        TextField tfDialogName = new TextField("Dialog name");
        tfDialogName.setValue(service.getCurrentDialog().getName());
        tfDialogName.setWidth("100%");
        tfDialogName.addValueChangeListener(event -> service.renameCurrentDialog(event.getProperty().getValue().toString()));

        Button bClear = new Button("Clear dialog");
        bClear.addClickListener(event -> service.clearCurrentDialog());

        MessagesPanel messagesPanel = new MessagesPanel();
        service.addListener(messagesPanel);

        correspondenceService.addListener(correspondencePanel);
        service.addListener(correspondencePanel);

        GridLayout grid = new GridLayout(3, 8);
        grid.addComponent(correspondencePanel, 0, 0, 0, 7);
        grid.addComponent(tfDialogName, 1, 0, 1, 0);
        grid.addComponent(messagesPanel, 1, 1, 1, 6);
        grid.addComponent(newMessagePanel, 1, 7, 1, 7);
        grid.addComponent(bClear, 2, 7, 2, 7);
        grid.setSizeFull();
        addComponent(grid);

        grid.setComponentAlignment(bClear, Alignment.BOTTOM_RIGHT);

        setSizeFull();
        correspondencePanel.setValue(correspondenceService.getCurrentCorrespondence());
        service.fireCurrentDialogChanged();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
