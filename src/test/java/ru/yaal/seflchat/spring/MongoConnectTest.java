package ru.yaal.seflchat.spring;

import org.junit.Test;
import ru.yaal.seflchat.data.User;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class MongoConnectTest extends BaseMongoTest {
    @Test
    public void name() {
        uRepo.insert(new User("1", "admin", "abcd", null));
        assertThat(uRepo.count(), equalTo(1L));
    }
}