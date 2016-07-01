package ru.yaal.seflchat.spring;

import com.mongodb.Mongo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author Yablokov Aleksey
 */


@Configuration
@Log
class MongoConfiguration {

    @Autowired
    private MongoCredentials credentials;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        log.info("MongoDB configurations: " + credentials);
        UserCredentials userCredentials = new UserCredentials(credentials.getLogin(), credentials.getPassword());
        return new SimpleMongoDbFactory(new Mongo(credentials.getDbHost(), credentials.getDbPort()), credentials.getDbName(), userCredentials);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}


