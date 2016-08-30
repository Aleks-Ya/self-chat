package ru.yaal.seflchat.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yaal.seflchat.data.User;
import ru.yaal.seflchat.repository.UserDao;

/**
 * @author Yablokov Aleksey
 */
interface MongoUserDao extends MongoRepository<User, String>, UserDao {
}
