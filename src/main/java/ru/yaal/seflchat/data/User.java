package ru.yaal.seflchat.data;

import org.springframework.data.annotation.Id;

/**
 * @author Yablokov Aleksey
 */
public class User {
    @Id
    private String id;
    private String login;
    private String passwordHash;
    private String cookieValue;

    public User(String id, String login, String passwordHash, String cookieValue) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.cookieValue = cookieValue;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", cookieValue='" + cookieValue + '\'' +
                '}';
    }
}
