package ru.yaal.selfchat.server.repository;

import java.util.List;

import ru.yaal.selfchat.server.data.UserEntity;

public interface UserRespository {
	UserEntity create(UserEntity user);

	UserEntity get(int userId);

	List<UserEntity> getAll();
	
	UserEntity update(UserEntity user);

	boolean delete(int userId);
}
