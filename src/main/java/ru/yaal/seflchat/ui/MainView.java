package ru.yaal.seflchat.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yaal.seflchat.service.DataService;

/**
 * @author Yablokov Aleksey
 */
@SpringView(name = "")
class MainView extends VerticalLayout implements View {

    @Autowired
    MainView(NewMessagePanel newMessagePanel, DataService dataService, MessagesPanel messagesPanel) {
        dataService.getCurrentDialog().getMessages().forEach(messagesPanel::addMessage);

        GridLayout grid = new GridLayout(3, 8);
        grid.addComponent(messagesPanel, 1, 0, 1, 6);
        grid.addComponent(newMessagePanel, 1, 7, 1, 7);
        grid.setSizeFull();
        addComponent(grid);

        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
