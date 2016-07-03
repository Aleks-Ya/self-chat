package ru.yaal.seflchat.service;

import ru.yaal.seflchat.data.User;

import java.util.Optional;

/**
 * @author Yablokov Aleksey
 */
public interface UserService {

    User createUser(User newUser);

    User getCurrentUser();

    void setCurrentUser(User user);

    Optional<User> getUserByCookie(String cookie);
}
