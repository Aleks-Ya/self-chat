package ru.yaal.seflchat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Yablokov Aleksey
 */
@SpringBootApplication
@ComponentScan
@Slf4j
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        log.info("Start Application");
        SpringApplication.run(Application.class, args);
    }
}
