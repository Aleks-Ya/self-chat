package ru.yaal.seflchat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.User;

import java.util.Optional;

/**
 * @author Yablokov Aleksey
 */
public interface CorrespondenceRepository extends MongoRepository<Correspondence, String> {
    Optional<Correspondence> findByUser(User user);
}
