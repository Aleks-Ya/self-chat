package ru.yaal.selfchat.server.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.yaal.selfchat.domian.security.UserEntity;
import ru.yaal.selfchat.server.core.service.JsonService;
import ru.yaal.selfchat.server.core.service.UserService;

@RestController
public class UserController {

	private static final String JSON_MIME_TYPE = "application/json;charset=UTF-8";
	
	@Autowired
	private UserService userService;

	@Autowired
	private JsonService jsonService;

	/**
	 * Test: curl -H "Content-Type: application/json" -X POST --data "{id: 1, login: aleks, passwordHash: abc}" localhost:8080/server-1.0/user
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = JSON_MIME_TYPE)
	public String createUser(@RequestBody UserEntity user) {
		final UserEntity newUser = userService.createUser(user);
		return jsonService.userToJson(newUser);
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = JSON_MIME_TYPE)
	public String getUser(@PathVariable int userId) {
		final UserEntity user = userService.getUser(userId);
		return jsonService.userToJson(user);
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT, produces = JSON_MIME_TYPE)
	public String updateUser(@PathVariable int userId, @RequestBody UserEntity user) {
		assert userId == user.getId();
		userService.updateUser(user);
		return "update " + userId;
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE, produces = JSON_MIME_TYPE)
	public String deleteUser(@PathVariable int userId) {
		return String.valueOf(userService.deleteUser(userId));
	}

	/**
	 * Test: curl localhost:8080/server-1.0/users
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = JSON_MIME_TYPE)
	public String getAllUsers() {
		final List<UserEntity> allUsers = userService.getAllUsers();
		return jsonService.usersToJson(allUsers);
	}

}
