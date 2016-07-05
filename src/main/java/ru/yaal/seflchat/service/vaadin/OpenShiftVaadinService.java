package ru.yaal.seflchat.service.vaadin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.User;
import ru.yaal.seflchat.spring.SpringProfiles;

import javax.servlet.http.Cookie;


/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(SpringProfiles.OPEN_SHIFT)
class OpenShiftVaadinService extends AbstractVaadinService {

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
    public void setUserToSession(User user) {
        VaadinSession.getCurrent().setAttribute(currentUserAttr, user);
    }

    @Override
    public User getUserFromSession() {
        return (User) VaadinSession.getCurrent().getAttribute(currentUserAttr);
    }

    @Override
    public Correspondence getCorrespondenceFromSession() {
        return (Correspondence) VaadinSession.getCurrent().getAttribute(correspondenceAttr);
    }

    @Override
    public void setCorrespondenceToSession(Correspondence correspondence) {
        VaadinSession.getCurrent().setAttribute(correspondenceAttr, correspondence);
    }

    @Override
    public Dialog getDialogFromSession() {
        return (Dialog) VaadinSession.getCurrent().getAttribute(dialogAttr);
    }

    @Override
    public void setDialogToSession(Dialog dialog) {
        VaadinSession.getCurrent().setAttribute(dialogAttr, dialog);
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
}
