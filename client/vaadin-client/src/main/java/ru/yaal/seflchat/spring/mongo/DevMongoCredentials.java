package ru.yaal.seflchat.spring.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.spring.SpringProfiles;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(SpringProfiles.DEV)
class DevMongoCredentials extends AbstractMongoCredentials {

    @Override
    public String getDbHost() {
        return "localhost";
    }

    @Override
    public int getDbPort() {
        return 27017;
    }

    @Override
    public String getDbName() {
        return "selfchat";
    }

    @Override
    public String getLogin() {
        return "";
    }

    @Override
    public String getPassword() {
        return "";
    }
}
