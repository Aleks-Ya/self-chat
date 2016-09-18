package ru.yaal.selfchat.server;

import java.util.Properties;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import ru.yaal.selfchat.server.api.rest.controller.UserController;
import ru.yaal.selfchat.server.config.TestRootConfig;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestRootConfig.class })
public abstract class BaseSpringTest {
	
	protected MockMvc mockMvc;
	@Autowired
	private UserController controller;

	@Before
	public void setUp() throws ServletException {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setHandlerExceptionResolvers(exceptionResolver()).build();
	}

	private HandlerExceptionResolver exceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

		Properties exceptionMappings = new Properties();

		exceptionMappings.put("net.petrikainulainen.spring.testmvc.todo.exception.TodoNotFoundException", "error/404");
		exceptionMappings.put("java.lang.Exception", "error/error");
		exceptionMappings.put("java.lang.RuntimeException", "error/error");

		exceptionResolver.setExceptionMappings(exceptionMappings);

		Properties statusCodes = new Properties();

		statusCodes.put("error/404", "404");
		statusCodes.put("error/error", "500");

		exceptionResolver.setStatusCodes(statusCodes);

		return exceptionResolver;
	}
}
