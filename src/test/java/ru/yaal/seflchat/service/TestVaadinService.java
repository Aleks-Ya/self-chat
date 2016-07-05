package ru.yaal.seflchat.service;

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
@Profile(SpringProfiles.TEST)
class TestVaadinService extends AbstractVaadinService {
    private Cookie cookie;
    private User user;
    private Correspondence correspondence;
    private Dialog dialog;

    @Override
    public Cookie getUserCookie() {
        return cookie;
    }

    @Override
    public void setUserCookie(String value) {
        cookie = createUserCookie(value);
    }

    @Override
    public void setUserToSession(User user) {
        this.user = user;
    }

    @Override
    public User getUserFromSession() {
        return user;
    }

    @Override
    public Correspondence getCorrespondenceFromSession() {
        return correspondence;
    }

    @Override
    public void setCorrespondenceToSession(Correspondence correspondence) {
        this.correspondence = correspondence;
    }

    @Override
    public Dialog getDialogFromSession() {
        return dialog;
    }

    @Override
    public void setDialogToSession(Dialog dialog) {
        this.dialog = dialog;
    }
}
