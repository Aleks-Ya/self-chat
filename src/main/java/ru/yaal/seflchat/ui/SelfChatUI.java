package ru.yaal.seflchat.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import lombok.extern.java.Log;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("WeakerAccess")
@SpringUI
@Log
public class SelfChatUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        log.info("Create " + getClass().getSimpleName());
        Navigator n = new Navigator(this, this);

        SpringViewProvider provider = WebApplicationContextUtils.getRequiredWebApplicationContext(
                VaadinServlet.getCurrent().getServletContext()).getBean(SpringViewProvider.class);

        n.addProvider(provider);
        setSizeFull();
    }
}
