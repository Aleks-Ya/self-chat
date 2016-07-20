package ru.yaal.seflchat.repository.spring;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.repository.CorrespondenceDao;

/**
 * @author Yablokov Aleksey
 */
interface CorrespondenceSpringDaoImpl extends MongoRepository<Correspondence, String>, CorrespondenceDao {
}
