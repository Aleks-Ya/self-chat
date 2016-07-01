package ru.yaal.seflchat.spring;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile("openshift")
class OpenShiftMongoCredentials implements MongoCredentials {
    @Override
    public String getDbHost() {
        return System.getenv("OPENSHIFT_MONGODB_DB_HOST");
    }

    @Override
    public int getDbPort() {
        return Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
    }

    @Override
    public String getDbName() {
        return "selfchat";
    }

    @Override
    public String getLogin() {
        return System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
    }

    @Override
    public String getPassword() {
        return System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
    }
}
