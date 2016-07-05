package ru.yaal.seflchat.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.spring.SpringProfiles;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(SpringProfiles.DEV)
class DevVaadinService extends OpenShiftVaadinService {
}
