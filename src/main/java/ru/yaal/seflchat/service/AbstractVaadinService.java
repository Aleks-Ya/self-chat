package ru.yaal.seflchat.service;

import javax.servlet.http.Cookie;

/**
 * @author Yablokov Aleksey
 */
abstract class AbstractVaadinService implements VaadinService {
    static final String cookieName = "self-chat";
    static final String currentUserAttr = "userAttr";
    static final String correspondenceAttr = "correspondenceAttr";
    static final String dialogAttr = "dialogAttr";

    Cookie createUserCookie(String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(Integer.MAX_VALUE);
        return cookie;
    }
}
