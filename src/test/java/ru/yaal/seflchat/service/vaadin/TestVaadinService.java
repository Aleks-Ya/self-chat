package ru.yaal.seflchat.service.vaadin;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.spring.SpringProfiles;

import javax.servlet.http.Cookie;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(SpringProfiles.TEST)
class TestVaadinService extends AbstractVaadinService {
    private Cookie cookie;
    private String userId;
    private String correspondenceId;
    private String dialogId;

    @Override
    public Cookie getUserCookie() {
        return cookie;
    }

    @Override
    public void setUserCookie(String value) {
        cookie = createUserCookie(value);
    }

    @Override
    public void setUserIdToSession(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUserIdFromSession() {
        return userId;
    }

    @Override
    public String getCorrespondenceIdFromSession() {
        return correspondenceId;
    }

    @Override
    public void setCorrespondenceIdToSession(String correspondenceId) {
        this.correspondenceId = correspondenceId;
    }

    @Override
    public String getDialogIdFromSession() {
        return dialogId;
    }

    @Override
    public void setDialogIdToSession(String dialogId) {
        this.dialogId = dialogId;
    }
}
