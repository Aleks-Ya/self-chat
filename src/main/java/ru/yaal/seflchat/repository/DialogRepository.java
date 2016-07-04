package ru.yaal.seflchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yaal.seflchat.data.Dialog;

/**
 * @author Yablokov Aleksey
 */
public interface DialogRepository extends MongoRepository<Dialog, String> {
}
