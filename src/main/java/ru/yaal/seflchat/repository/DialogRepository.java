package ru.yaal.seflchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yaal.seflchat.data.Dialog;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public interface DialogRepository extends MongoRepository<Dialog, String> {
    List<Dialog> findByUserId(String login);
}
