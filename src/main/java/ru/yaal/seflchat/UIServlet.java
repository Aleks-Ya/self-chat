package ru.yaal.seflchat;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import ru.yaal.seflchat.ui.SelfChatUI;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", name = "UIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = SelfChatUI.class, productionMode = false)
public class UIServlet extends VaadinServlet {
}
