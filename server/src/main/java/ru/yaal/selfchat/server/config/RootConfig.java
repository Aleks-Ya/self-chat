package ru.yaal.selfchat.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.yaal.selfchat.domian.security.UserEntity;
import ru.yaal.selfchat.server.core.repository.UserRespository;
import ru.yaal.selfchat.server.core.service.UserService;

@Configuration
@ComponentScan(basePackageClasses = { UserEntity.class, UserRespository.class, UserService.class })
public class RootConfig {
}
