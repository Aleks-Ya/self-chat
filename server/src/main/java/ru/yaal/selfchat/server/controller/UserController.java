package ru.yaal.selfchat.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.yaal.selfchat.domian.security.UserEntity;
import ru.yaal.selfchat.server.service.JsonService;
import ru.yaal.selfchat.server.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JsonService jsonService;

	/**
	 * Test: curl -H "Content-Type: application/json" -X POST --data "{id: 1, login: aleks, passwordHash: abc}" localhost:8080/server-1.0/user
	 */
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

	/**
	 * Test: curl localhost:8080/server-1.0/users
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getAllUsers() {
		final List<UserEntity> allUsers = userService.getAllUsers();
		return jsonService.usersToJson(allUsers);
	}

}
