package ru.yaal.selfchat.server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String createUser() {
		return "created";
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public String getUser(@PathVariable int userId) {
		return "get " + userId;
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
	public String updateUser(@PathVariable int userId) {
		return "update " + userId;
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable int userId) {
		return "delete " + userId;
	}

	@RequestMapping(value = "/users}", method = RequestMethod.GET)
	public String getAllUsers() {
		return "find all";
	}

}
