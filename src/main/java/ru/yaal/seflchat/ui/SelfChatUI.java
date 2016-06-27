package ru.yaal.seflchat.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import ru.yaal.seflchat.data.BeanDataSource;
import ru.yaal.seflchat.data.TestBeanDataSource;

@SuppressWarnings("WeakerAccess")
public class SelfChatUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator n = new Navigator(this, this);
        n.addView("", new MainView(new BeanDataSource()));
        String testName = "test";
        n.addView(testName, new MainView(new TestBeanDataSource()));
        if ("/test".equals(vaadinRequest.getPathInfo())) {
            n.navigateTo(testName);
        } else {
            n.navigateTo("");
        }
        setSizeFull();
    }
}
