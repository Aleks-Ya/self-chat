package ru.yaal.selfchat.server.config.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.yaal.selfchat.server.config.rest.mvc.DispatcherServletConfig;
import ru.yaal.selfchat.server.config.rest.mvc.WebMvcConfigurer;
import ru.yaal.selfchat.server.config.rest.security.SecurityConfig;

@Configuration
@Import({ DispatcherServletConfig.class, WebMvcConfigurer.class, SecurityConfig.class })
public class RestApiConfig {
}
