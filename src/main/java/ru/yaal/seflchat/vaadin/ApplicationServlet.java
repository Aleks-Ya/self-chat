package ru.yaal.seflchat.vaadin;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(ApplicationServlet.class);

    @Override
    protected void servletInitialized() throws ServletException {
        log.info("Initialize ApplicationServlet");
        super.servletInitialized();
        SessionListener listener = WebApplicationContextUtils.getRequiredWebApplicationContext(
                this.getServletContext()).getBean(SessionListener.class);
        getService().addSessionInitListener(listener);
        getService().addSessionDestroyListener(listener);
    }
}
