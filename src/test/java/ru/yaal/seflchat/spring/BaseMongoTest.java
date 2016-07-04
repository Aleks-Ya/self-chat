package ru.yaal.seflchat.spring;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yaal.seflchat.repository.CorrespondenceRepository;
import ru.yaal.seflchat.repository.UserRepository;
import ru.yaal.seflchat.service.CorrespondenceService;

/**
 * @author Yablokov Aleksey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles(SpringProfiles.TEST)
public abstract class BaseMongoTest {
    @Autowired
    protected UserRepository uRepo;
    @Autowired
    protected CorrespondenceRepository cRepo;
    @Autowired
    protected CorrespondenceService cs;
}