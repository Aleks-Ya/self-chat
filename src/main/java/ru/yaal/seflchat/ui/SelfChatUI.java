package ru.yaal.seflchat.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.yaal.seflchat.service.UserService;

@SuppressWarnings("WeakerAccess")
@SpringUI
@Slf4j
public class SelfChatUI extends UI {

    @Autowired
    private UserService userService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        log.info("Create " + getClass().getSimpleName());
        userService.initCurrentUser();
        Navigator n = new Navigator(this, this);

        SpringViewProvider provider = WebApplicationContextUtils.getRequiredWebApplicationContext(
                VaadinServlet.getCurrent().getServletContext()).getBean(SpringViewProvider.class);

        n.addProvider(provider);
        setSizeFull();
    }
}
