package ru.yaal.selfchat.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.yaal.selfchat.server.data.UserEntity;
import ru.yaal.selfchat.server.service.JsonService;
import ru.yaal.selfchat.server.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private JsonService jsonService;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String createUser(@RequestBody UserEntity user) {
		final UserEntity newUser = userService.createUser(user);
		return jsonService.userToJson(newUser);
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public String getUser(@PathVariable int userId) {
		final UserEntity user = userService.getUser(userId);
		return jsonService.userToJson(user);
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
	public String updateUser(@PathVariable int userId, @RequestBody UserEntity user) {
		assert userId == user.getId();
		userService.updateUser(user);
		return "update " + userId;
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable int userId) {
		return String.valueOf(userService.deleteUser(userId));
	}

	@RequestMapping(value = "/users}", method = RequestMethod.GET)
	public String getAllUsers() {
		final List<UserEntity> allUsers = userService.getAllUsers();
		return jsonService.usersToJson(allUsers);
	}

}
