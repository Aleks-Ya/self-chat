package ru.yaal.selfchat.server.core.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.yaal.selfchat.domian.security.UserEntity;

@Component
public class JsonServiceImpl implements JsonService {
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public String userToJson(UserEntity user) {
		return gson.toJson(user);
	}

	@Override
	public UserEntity jsonToUser(String jsonUser) {
		return gson.fromJson(jsonUser, UserEntity.class);
	}

	@Override
	public String usersToJson(List<UserEntity> users) {
		return gson.toJson(users);
	}

}
