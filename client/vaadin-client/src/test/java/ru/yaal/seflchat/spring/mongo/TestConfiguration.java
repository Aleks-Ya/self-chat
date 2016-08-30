package ru.yaal.seflchat.spring.mongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.yaal.seflchat.spring.SpringProfiles;

/**
 * @author Yablokov Aleksey
 */


@Configuration
@Slf4j
@Profile(SpringProfiles.TEST)
@ComponentScan(basePackages = {"ru.yaal.seflchat.data", "ru.yaal.seflchat.spring", "ru.yaal.seflchat.service"})
@EnableMongoRepositories(basePackages = "ru.yaal.seflchat.repository")
class TestConfiguration {

}


