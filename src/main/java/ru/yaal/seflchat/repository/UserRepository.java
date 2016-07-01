package ru.yaal.seflchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yaal.seflchat.data.User;

/**
 * @author Yablokov Aleksey
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByLogin(String login);
}
