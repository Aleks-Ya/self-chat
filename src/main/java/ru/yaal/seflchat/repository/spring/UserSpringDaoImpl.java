package ru.yaal.seflchat.repository.spring;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yaal.seflchat.data.User;
import ru.yaal.seflchat.repository.UserDao;

import java.util.Optional;

/**
 * @author Yablokov Aleksey
 */
interface UserSpringDaoImpl extends MongoRepository<User, String>, UserDao {
}
