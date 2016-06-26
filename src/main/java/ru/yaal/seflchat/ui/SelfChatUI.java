package ru.yaal.seflchat.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@SuppressWarnings("WeakerAccess")
public class SelfChatUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator n = new Navigator(this, this);
        n.addView("", new MainView());
        setSizeFull();
    }
}
