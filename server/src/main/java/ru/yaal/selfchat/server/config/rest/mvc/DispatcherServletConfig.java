package ru.yaal.selfchat.server.config.rest.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ru.yaal.selfchat.server.api.rest.controller.UserController;

/**
 * Root of DispatcherServlet's application context
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {UserController.class })
//@Import(WebMvcConfigurer.class)
public class DispatcherServletConfig {

}
