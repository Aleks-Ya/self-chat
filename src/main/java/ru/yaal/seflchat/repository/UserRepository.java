package ru.yaal.seflchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yaal.seflchat.data.User;

import java.util.Optional;

/**
 * @author Yablokov Aleksey
 */
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByCookieValue(String cookieValue);
}
