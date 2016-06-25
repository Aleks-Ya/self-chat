package ru.yaal.seflchat;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import ru.yaal.seflchat.data.BeanDataSource;
import ru.yaal.seflchat.data.DataSource;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.MessageImpl;

@SuppressWarnings("WeakerAccess")
public class HelloWorldUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        DataSource ds = new BeanDataSource();
        Dialog dialog = ds.getDialogs().get(0);
        for (MessageImpl message : dialog.getMessages()) {
            TextField textField = new TextField();
            textField.setValue(message.getMessage());
            layout.addComponent(textField);
        }

        setContent(layout);
    }
}
