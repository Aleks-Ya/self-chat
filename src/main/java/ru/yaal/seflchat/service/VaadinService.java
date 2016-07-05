package ru.yaal.seflchat.service;

import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.User;

import javax.servlet.http.Cookie;

/**
 * @author Yablokov Aleksey
 */
public interface VaadinService {
    Cookie getUserCookie();

    void setUserCookie(String value);

    void setUserToSession(User user);

    User getUserFromSession();

    Correspondence getCorrespondenceFromSession();

    void setCorrespondenceToSession(Correspondence correspondence);

    Dialog getDialogFromSession();

    void setDialogToSession(Dialog dialog);
}
