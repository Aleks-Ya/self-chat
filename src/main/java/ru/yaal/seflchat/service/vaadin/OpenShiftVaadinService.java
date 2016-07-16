package ru.yaal.seflchat.service.vaadin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.spring.SpringProfiles;

import javax.servlet.http.Cookie;


/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(SpringProfiles.OPEN_SHIFT)
class OpenShiftVaadinService extends AbstractVaadinService {

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
    public Cookie getUserCookie() {
        return getCookieByName(com.vaadin.server.VaadinService.getCurrentRequest(), cookieName);
    }

    @Override
    public void setUserCookie(String value) {
        Cookie cookie = createUserCookie(value);
        com.vaadin.server.VaadinService.getCurrentResponse().addCookie(cookie);
    }

    @Override
    public void setUserIdToSession(String userId) {
        VaadinSession.getCurrent().setAttribute(currentUserIdAttr, userId);
    }

    @Override
    public String getUserIdFromSession() {
        return (String) VaadinSession.getCurrent().getAttribute(currentUserIdAttr);
    }

    @Override
    public String getCorrespondenceIdFromSession() {
        return (String) VaadinSession.getCurrent().getAttribute(correspondenceIdAttr);
    }

    @Override
    public void setCorrespondenceIdToSession(String correspondenceId) {
        VaadinSession.getCurrent().setAttribute(correspondenceIdAttr, correspondenceId);
    }

    @Override
    public String getDialogIdFromSession() {
        return (String) VaadinSession.getCurrent().getAttribute(dialogIdAttr);
    }

    @Override
    public void setDialogIdToSession(String dialogId) {
        VaadinSession.getCurrent().setAttribute(dialogIdAttr, dialogId);
    }
}
