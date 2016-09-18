package ru.yaal.selfchat.server.core.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ru.yaal.selfchat.domian.security.UserEntity;
import ru.yaal.selfchat.server.core.repository.UserRespository;

@Component
class InMemoryUserRepository implements UserRespository {

	private final Map<Integer, UserEntity> users = new HashMap<>();
	private int nextId = 0;

	@Override
	public UserEntity create(UserEntity user) {
		if (user.getId() == null) {
			user = user.withId(nextId);
			nextId++;
			users.put(user.getId(), user);
		} else {
			if (users.containsKey(user.getId())) {
				throw new IllegalArgumentException("User already exists: " + user.getId());
			} else {
				users.put(user.getId(), user);
			}
		}
		return user;
	}

	@Override
	public UserEntity get(int userId) {
		return users.get(userId);
	}

	@Override
	public List<UserEntity> getAll() {
		return new ArrayList<>(users.values());
	}

	@Override
	public UserEntity update(UserEntity user) {
		assert user.getId() != null;
		assert users.containsKey(user.getId());
		return users.put(user.getId(), user);
	}

	@Override
	public boolean delete(int userId) {
		return users.remove(userId) != null;
	}

}
