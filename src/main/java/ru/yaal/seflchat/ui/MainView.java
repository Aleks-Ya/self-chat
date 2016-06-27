package ru.yaal.seflchat.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import ru.yaal.seflchat.data.BeanDataSource;
import ru.yaal.seflchat.data.DataSource;
import ru.yaal.seflchat.data.Dialog;

/**
 * @author Yablokov Aleksey
 */
public class MainView extends VerticalLayout implements View {
    public MainView() {
        DataSource ds = new BeanDataSource();
        Dialog dialog = ds.getDialogs().get(0);

        MessagesPanel messagesPanel = new MessagesPanelImpl();
        dialog.getMessages().forEach(messagesPanel::addMessage);
        Component messagePanel1 = (Component) messagesPanel;

        NewMessagePanel newMessagePanel = new NewMessagePanel(dialog, messagesPanel);

        GridLayout grid = new GridLayout(3, 8);

        grid.addComponent(messagePanel1, 1, 0, 1, 6);
        grid.addComponent(newMessagePanel, 1, 7, 1, 7);
        grid.setSizeFull();
        addComponent(grid);

        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
