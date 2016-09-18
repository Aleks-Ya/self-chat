package ru.yaal.selfchat.server.core.service;

import java.util.List;

import ru.yaal.selfchat.domian.security.UserEntity;


public interface UserService {
	UserEntity createUser(UserEntity newUser);
	UserEntity updateUser(UserEntity user);
	UserEntity getUser(int userId);
	boolean deleteUser(int userId);
	List<UserEntity> getAllUsers();
}
