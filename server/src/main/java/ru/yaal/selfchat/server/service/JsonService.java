package ru.yaal.selfchat.server.service;

import java.util.List;

import ru.yaal.selfchat.server.data.UserEntity;

public interface JsonService {

	String userToJson(UserEntity user);

	UserEntity jsonToUser(String jsonUser);

	String usersToJson(List<UserEntity> users);

}
