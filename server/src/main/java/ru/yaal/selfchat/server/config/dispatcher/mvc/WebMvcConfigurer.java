package ru.yaal.selfchat.server.config.dispatcher.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ru.yaal.selfchat.server.controller.UserConverter;

@Configuration
class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Autowired
	private UserConverter userConverter;

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(0, userConverter);
	}
}