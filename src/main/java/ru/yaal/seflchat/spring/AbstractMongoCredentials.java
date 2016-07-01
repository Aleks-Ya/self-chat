package ru.yaal.seflchat.spring;

/**
 * @author Yablokov Aleksey
 */
abstract class AbstractMongoCredentials implements MongoCredentials {

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{host=" + getDbHost() + ", port=" + getDbPort() + ", dbname=" + getDbName() + ", login=" + getLogin() + "}";
    }
}
