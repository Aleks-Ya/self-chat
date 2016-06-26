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

        NewMessagePanel newMessagePanel = new NewMessagePanel(dialog, null);

        MessagePanel messagePanel = new MessagePanelImpl();
        dialog.getMessages().forEach(messagePanel::addMessage);
        Component messagePanel1 = (Component) messagePanel;

        GridLayout grid = new GridLayout(3, 2);

        grid.addComponent(messagePanel1, 1, 0);
        grid.addComponent(newMessagePanel, 1, 1);
        grid.setRowExpandRatio(0, 1);
        grid.setSizeFull();
        addComponent(grid);

        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
