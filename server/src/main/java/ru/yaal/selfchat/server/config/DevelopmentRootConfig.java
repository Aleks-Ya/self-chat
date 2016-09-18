package ru.yaal.selfchat.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.yaal.selfchat.server.config.repository.InMemoryRepositoryConfig;
import ru.yaal.selfchat.server.config.rest.RestApiConfig;
import ru.yaal.selfchat.server.config.service.ServiceConfig;

@Configuration
@Import({InMemoryRepositoryConfig.class, ServiceConfig.class, RestApiConfig.class})
public class DevelopmentRootConfig {
}
