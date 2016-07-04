package ru.yaal.seflchat.service;

import com.vaadin.server.VaadinSession;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.spring.SpringProfiles;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(SpringProfiles.OPEN_SHIFT)
class OpenShiftVaadinService implements VaadinService {
    @Override
    public VaadinSession getCurrentVaadinSession() {
        return VaadinSession.getCurrent();
    }
}
