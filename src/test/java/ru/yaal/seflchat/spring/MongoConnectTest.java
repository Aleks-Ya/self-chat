package ru.yaal.seflchat.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yaal.seflchat.data.User;
import ru.yaal.seflchat.repository.UserRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Yablokov Aleksey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles(SpringProfiles.TEST)
public class MongoConnectTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void name() {
        userRepository.insert(new User("1", "admin", "abcd", null));
        assertThat(userRepository.count(), equalTo(1L));
    }
}