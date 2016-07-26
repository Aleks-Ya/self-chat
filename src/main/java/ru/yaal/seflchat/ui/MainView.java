package ru.yaal.seflchat.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;
import ru.yaal.seflchat.service.event.EventService;
import ru.yaal.seflchat.ui.center.CenterPanel;
import ru.yaal.seflchat.ui.left.CorrespondencePanel;

/**
 * @author Yablokov Aleksey
 */
@SpringView(name = "")
@Slf4j
class MainView extends VerticalLayout implements View {
    private final EventService eventService;
    private final CorrespondenceService corService;

    @Autowired
    MainView(CenterPanel centerPanel, CorrespondencePanel correspondencePanel,
             CorrespondenceService corService, EventService eventService) {
        log.info("Create " + getClass().getSimpleName());

        this.corService = corService;
        this.eventService = eventService;

        Button bClear = new Button("Clear dialog");
        bClear.addClickListener(event -> corService.clearCurrentDialog());

        GridLayout grid = new GridLayout(3, 8);
        grid.addComponent(correspondencePanel, 0, 0, 0, 7);
        grid.addComponent(centerPanel, 1, 0, 1, 7);
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
}
