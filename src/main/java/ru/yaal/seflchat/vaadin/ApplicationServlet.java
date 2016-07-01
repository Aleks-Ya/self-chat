package ru.yaal.seflchat.vaadin;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.yaal.seflchat.ui.SelfChatUI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * @author Yablokov Aleksey
 */
@WebServlet(urlPatterns = "/*", name = "UIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = SelfChatUI.class, productionMode = false)
public class ApplicationServlet extends VaadinServlet {

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        SessionListener listener = WebApplicationContextUtils.getRequiredWebApplicationContext(
                this.getServletContext()).getBean(SessionListener.class);
        getService().addSessionInitListener(listener);
        getService().addSessionDestroyListener(listener);
    }
}
