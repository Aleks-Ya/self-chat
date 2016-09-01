package ru.yaal.selfchat.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import ru.yaal.selfchat.server.data.UserEntity;
import ru.yaal.selfchat.server.service.JsonService;

@Component
public class UserConverter extends AbstractHttpMessageConverter<UserEntity> {

	@Autowired
	private JsonService json;

	public UserConverter() {
		super(new MediaType("application", "json", Charset.forName("UTF-8")));
	}

	@Override
	protected UserEntity readInternal(Class<? extends UserEntity> clazz, HttpInputMessage message)
			throws IOException, HttpMessageNotReadableException {
		try (InputStream is = message.getBody()) {
			BufferedReader dis = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String str = dis.lines().collect(Collectors.joining("\n"));
			return json.jsonToUser(str);
		}
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return clazz.equals(UserEntity.class);
	}

	@Override
	protected void writeInternal(UserEntity user, HttpOutputMessage message)
			throws IOException, HttpMessageNotWritableException {
		throw new UnsupportedOperationException();
	}
}
