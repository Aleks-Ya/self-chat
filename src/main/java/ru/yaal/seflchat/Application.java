package ru.yaal.seflchat;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Yablokov Aleksey
 */
@SpringBootApplication
@ComponentScan
@Log
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        log.info("Start Application");
        SpringApplication.run(Application.class, args);
    }
}
