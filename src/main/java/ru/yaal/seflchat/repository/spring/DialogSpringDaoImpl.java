package ru.yaal.seflchat.repository.spring;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.repository.DialogDao;

/**
 * @author Yablokov Aleksey
 */
interface DialogSpringDaoImpl extends MongoRepository<Dialog, String>, DialogDao {
}
