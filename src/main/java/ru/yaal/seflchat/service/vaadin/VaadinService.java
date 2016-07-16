package ru.yaal.seflchat.service.vaadin;

import javax.servlet.http.Cookie;

/**
 * @author Yablokov Aleksey
 */
public interface VaadinService {
    Cookie getUserCookie();

    void setUserCookie(String value);

    void setUserIdToSession(String userId);

    String getUserIdFromSession();

    String getCorrespondenceIdFromSession();

    void setCorrespondenceIdToSession(String correspondenceId);

    String getDialogIdFromSession();

    void setDialogIdToSession(String dialogId);
}
