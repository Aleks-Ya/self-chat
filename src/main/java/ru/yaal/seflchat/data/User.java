package ru.yaal.seflchat.data;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Yablokov Aleksey
 */
@Getter
@Document
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
