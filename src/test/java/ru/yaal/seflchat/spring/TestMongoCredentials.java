package ru.yaal.seflchat.spring;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(SpringProfiles.TEST)
class TestMongoCredentials extends AbstractMongoCredentials {

    @Override
    public String getDbHost() {
        return "localhost";
    }

    @Override
    public int getDbPort() {
        return 27018;
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
