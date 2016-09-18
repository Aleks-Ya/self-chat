package ru.yaal.selfchat.server.config.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ru.yaal.selfchat.server.core.repository.impl.postgres")
public class PostgresRepositoryConfig {
}
