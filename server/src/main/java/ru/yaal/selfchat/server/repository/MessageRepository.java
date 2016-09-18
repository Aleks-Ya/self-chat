package ru.yaal.selfchat.server.repository;

import java.util.List;

import ru.yaal.selfchat.domian.UserEntity;

public interface MessageRepository {
	UserEntity create(UserEntity user);

	UserEntity get(int userId);

	List<UserEntity> getAll();
	
	UserEntity update(UserEntity user);

	boolean delete(int userId);
}
