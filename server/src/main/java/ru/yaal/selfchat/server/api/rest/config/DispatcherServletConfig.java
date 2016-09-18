package ru.yaal.selfchat.server.api.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ru.yaal.selfchat.server.api.rest.controller.UserController;

/**
 * Root of DispatcherServlet's application context
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { DispatcherServletConfig.class, UserController.class })
public class DispatcherServletConfig {

}
