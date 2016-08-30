package ru.yaal.seflchat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.AbstractEnvironment;
import ru.yaal.seflchat.spring.SpringProfiles;

/**
 * @author Yablokov Aleksey
 */
@SpringBootApplication
@ComponentScan
@Slf4j
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        log.info("Start Application");
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, SpringProfiles.DEV);
        SpringApplication.run(Application.class, args);
    }
}
