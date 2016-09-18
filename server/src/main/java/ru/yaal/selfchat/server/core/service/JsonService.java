package ru.yaal.selfchat.server.core.service;

import java.util.List;

import ru.yaal.selfchat.domian.security.UserEntity;


public interface JsonService {

	String userToJson(UserEntity user);

	UserEntity jsonToUser(String jsonUser);

	String usersToJson(List<UserEntity> users);

}
