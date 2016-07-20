package ru.yaal.seflchat.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.repository.CorrespondenceDao;

/**
 * @author Yablokov Aleksey
 */
interface MongoCorrespondenceDao extends MongoRepository<Correspondence, String>, CorrespondenceDao {
}
