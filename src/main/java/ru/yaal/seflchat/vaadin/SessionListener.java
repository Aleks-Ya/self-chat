package ru.yaal.seflchat.vaadin;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.User;
import ru.yaal.seflchat.service.UserService;

import javax.servlet.http.Cookie;
import java.time.Instant;
import java.util.Optional;

/**
 * @author Yablokov Aleksey
 */
@Component
public class SessionListener implements SessionInitListener, SessionDestroyListener {
    private static final String cookieName = "self-char";
    public static final String currentUserAttr = "current-user";

    private final UserService service;

    @Autowired
    public SessionListener(UserService service) {
        this.service = service;
    }

    @Override
    public void sessionInit(SessionInitEvent event) throws ServiceException {
        Cookie cookie = getCookieByName(event.getRequest(), cookieName);
        User user = takeUser(cookie);
        service.setCurrentUser(user);
    }

    private User takeUser(Cookie cookie) {
        if (cookie != null) {
            Optional<User> user = service.getUserByCookie(cookie.getValue());
            return user.orElseGet(() -> createNewUser(cookie.getValue()));
        } else {
            Cookie newCookie = new Cookie(cookieName, Instant.now().toString());
            newCookie.setMaxAge(Integer.MAX_VALUE);
            VaadinService.getCurrentResponse().addCookie(newCookie);
            String cookieValue = newCookie.getValue();
            return createNewUser(cookieValue);
        }
    }

    private User createNewUser(String value) {
        return service.createUser(new User(null, "user_" + Instant.now(), "abcd", value));
    }

    private static Cookie getCookieByName(VaadinRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    @Override
    public void sessionDestroy(SessionDestroyEvent event) {
    }
}
