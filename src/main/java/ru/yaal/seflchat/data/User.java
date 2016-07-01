package ru.yaal.seflchat.data;

public class User {
    private String id;
    private String login;
    private String passwordHash;

    public User(String id, String login, String passwordHash) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
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
}
