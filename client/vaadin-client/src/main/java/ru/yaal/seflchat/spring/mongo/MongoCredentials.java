package ru.yaal.seflchat.spring.mongo;

/**
 * @author Yablokov Aleksey
 */
interface MongoCredentials {
    String getDbHost();
    int getDbPort();
    String getDbName();
    String getLogin();
    String getPassword();
}
