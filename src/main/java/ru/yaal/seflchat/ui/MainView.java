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
import ru.yaal.seflchat.service.CurrentDialogService;

/**
 * @author Yablokov Aleksey
 */
@SpringView(name = "")
@Slf4j
class MainView extends VerticalLayout implements View {

    @Autowired
    MainView(NewMessagePanel newMessagePanel, CurrentDialogService service, MessagesPanel messagesPanel) {
        log.info("Create " + getClass().getSimpleName());

        Button bClear = new Button("Clear dialog");
        bClear.addClickListener(event -> service.clearCurrentDialog());

        GridLayout grid = new GridLayout(3, 8);
        grid.addComponent(messagesPanel, 1, 0, 1, 6);
        grid.addComponent(newMessagePanel, 1, 7, 1, 7);
        grid.addComponent(bClear, 2, 7, 2, 7);
        grid.setSizeFull();
        addComponent(grid);

        grid.setComponentAlignment(bClear, Alignment.BOTTOM_RIGHT);

        setSizeFull();
        service.getCurrentDialog();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
