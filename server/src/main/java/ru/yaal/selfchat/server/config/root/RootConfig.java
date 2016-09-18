package ru.yaal.selfchat.server.config.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.yaal.selfchat.domian.security.UserEntity;
import ru.yaal.selfchat.server.repository.UserRespository;
import ru.yaal.selfchat.server.service.UserService;

@Configuration
@ComponentScan(basePackageClasses = { UserEntity.class, UserRespository.class, UserService.class })
public class RootConfig {
}
