package ru.yaal.seflchat.spring;

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
