package ru.yaal.seflchat.spring;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author Yablokov Aleksey
 */


@Configuration
public class MongoConfiguration {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
//        UserCredentials userCredentials = new UserCredentials("joe", "secret");
//        return new SimpleMongoDbFactory(new Mongo(), "database", userCredentials);
//        UserCredentials userCredentials = new UserCredentials("joe", "secret");
        return new SimpleMongoDbFactory(new Mongo(), "selfchat");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}


