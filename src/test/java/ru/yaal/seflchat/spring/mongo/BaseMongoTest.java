package ru.yaal.seflchat.spring.mongo;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yaal.seflchat.repository.CorrespondenceDao;
import ru.yaal.seflchat.repository.DialogRepository;
import ru.yaal.seflchat.repository.UserRepository;
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;
import ru.yaal.seflchat.spring.SpringProfiles;

/**
 * @author Yablokov Aleksey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles(SpringProfiles.TEST)
@SuppressWarnings("SpringJavaAutowiringInspection")
public abstract class BaseMongoTest {
    @Autowired
    protected UserRepository uRepo;
    @Autowired
    protected CorrespondenceDao cRepo;
    @Autowired
    protected DialogRepository dRepo;
    @Autowired
    protected CorrespondenceService cs;
}