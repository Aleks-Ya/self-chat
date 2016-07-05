package ru.yaal.seflchat.spring.mongo;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import ru.yaal.seflchat.spring.SpringProfiles;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author Yablokov Aleksey
 */


@Configuration
@Slf4j
@Profile(SpringProfiles.TEST)
class TestMongoConfiguration {

    @Autowired
    private MongoCredentials credentials;

    @PostConstruct
    private void startMongo() throws IOException {
        int port = credentials.getDbPort();
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build();
        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        log.info("MongoDB configurations: " + credentials);
        MongoClient mongo = new MongoClient(credentials.getDbHost(), credentials.getDbPort());
        return new SimpleMongoDbFactory(mongo, credentials.getDbName());
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}


