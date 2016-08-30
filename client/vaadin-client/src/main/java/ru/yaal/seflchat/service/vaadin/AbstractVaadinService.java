package ru.yaal.seflchat.service.vaadin;

import javax.servlet.http.Cookie;

/**
 * @author Yablokov Aleksey
 */
abstract class AbstractVaadinService implements VaadinService {
    static final String cookieName = "self-chat";
    static final String currentUserIdAttr = "userAttr";
    static final String correspondenceIdAttr = "correspondenceAttr";
    static final String dialogIdAttr = "dialogAttr";

    Cookie createUserCookie(String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(Integer.MAX_VALUE);
        return cookie;
    }
}
