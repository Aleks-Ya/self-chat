package ru.yaal.seflchat.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;
import ru.yaal.seflchat.service.event.DialogEvent;
import ru.yaal.seflchat.service.event.EventService;
import ru.yaal.seflchat.ui.center.MessagesPanel;
import ru.yaal.seflchat.ui.center.NewMessagePanel;
import ru.yaal.seflchat.ui.left.CorrespondencePanel;

/**
 * @author Yablokov Aleksey
 */
@SpringView(name = "")
@Slf4j
class MainView extends VerticalLayout implements View, EventService.DialogSelectedListener {
    private final TextField tfDialogName = new TextField("Dialog name");
    private final EventService eventService;
    private final CorrespondenceService corService;

    @Autowired
    MainView(NewMessagePanel newMessagePanel, CorrespondencePanel correspondencePanel,
             MessagesPanel messagesPanel, CorrespondenceService corService, EventService eventService) {
        log.info("Create " + getClass().getSimpleName());

        this.corService = corService;
        this.eventService = eventService;

        tfDialogName.setValue(corService.getCurrentDialog().getName());
        tfDialogName.setWidth("100%");
        tfDialogName.addValueChangeListener(event -> corService.renameCurrentDialog(event.getProperty().getValue().toString()));

        Button bClear = new Button("Clear dialog");
        bClear.addClickListener(event -> corService.clearCurrentDialog());

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
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Correspondence currentCorrespondence = corService.getCurrentCorrespondence();
        eventService.fireCorrespondenceSelected(currentCorrespondence);
    }

    @Override
    public void dialogSelected(DialogEvent event) {
        tfDialogName.setValue(event.getSelectedDialog().get().getName());
    }
}
