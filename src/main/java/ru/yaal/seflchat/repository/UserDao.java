package ru.yaal.seflchat.repository;

import ru.yaal.seflchat.data.User;

import java.util.Optional;

/**
 * @author Aleksey Yablokov
 */
public interface UserDao {
    Optional<User> findByCookieValue(String cookieValue);

    User save(User user);

    User insert(User user);

    User findOne(String userId);

    void delete(User user);

    long count();
}
