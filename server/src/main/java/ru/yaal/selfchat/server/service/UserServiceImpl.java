package ru.yaal.selfchat.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.yaal.selfchat.domian.security.UserEntity;
import ru.yaal.selfchat.server.repository.UserRespository;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRespository userRepo;

	@Override
	public UserEntity createUser(UserEntity newUser) {
		return userRepo.create(newUser);
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		return userRepo.update(user);
	}

	@Override
	public UserEntity getUser(int userId) {
		return userRepo.get(userId);
	}

	@Override
	public boolean deleteUser(int userId) {
		return userRepo.delete(userId);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepo.getAll();
	}

}
