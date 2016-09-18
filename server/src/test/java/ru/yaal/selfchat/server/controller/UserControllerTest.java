package ru.yaal.selfchat.server.controller;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.yaal.selfchat.domian.security.UserEntity;
import ru.yaal.selfchat.server.BaseSpringTest;
import ru.yaal.selfchat.server.service.JsonService;

public class UserControllerTest extends BaseSpringTest {

	@Autowired
	private UserController controller;
	@Autowired
	private JsonService json;

	@Test
	public void createUserGenerateId() {
		UserEntity expUser = new UserEntity(null, "a", "hash");
		String jsonUser = controller.createUser(expUser);
		UserEntity actUser = json.jsonToUser(jsonUser);
		assertThat(actUser.getId(), notNullValue());
		assertThat(actUser, equalTo(expUser.withId(actUser.getId())));
	}

	@Test
	public void createUserWithId() {
		int id = 2;
		UserEntity expUser = new UserEntity(id, "с", "hash");
		String jsonUser = controller.createUser(expUser);
		UserEntity actUser = json.jsonToUser(jsonUser);
		assertThat(actUser.getId(), notNullValue());
		assertThat(actUser, equalTo(expUser));
	}

	@Test
	public void getUser() {
		int id = 3;
		UserEntity expUser = new UserEntity(id, "d", "hash");
		controller.createUser(expUser);

		String jsonUser = controller.getUser(id);
		assertThat(jsonUser, notNullValue());

		UserEntity actUser = json.jsonToUser(jsonUser);
		assertThat(actUser, equalTo(expUser));
	}

	@Test
	public void updateUser() {
		int id = 4;
		UserEntity expUser = new UserEntity(id, "o", "hash");
		controller.createUser(expUser);

		UserEntity actUser = json.jsonToUser(controller.getUser(id));
		assertThat(actUser, equalTo(expUser));
		
		UserEntity expUserUpdated = expUser.withLogin("newLogin");
		controller.updateUser(id, expUserUpdated);
		
		UserEntity actUserUpdated = json.jsonToUser(controller.getUser(id));
		assertThat(actUserUpdated, equalTo(expUserUpdated));
	}

	@Test
	public void deleteUser() {
		int id = 5;
		UserEntity expUser = new UserEntity(id, "o", "hash");
		controller.createUser(expUser);

		UserEntity actUser = json.jsonToUser(controller.getUser(id));
		assertThat(actUser, equalTo(expUser));
		
		controller.deleteUser(id);
		
		assertThat(controller.getUser(id), equalTo("null"));
	}

	@Test
	public void testGetAllUsers() {
		String login1 = "first";
		UserEntity expUser1 = new UserEntity(6, login1, "hash");
		controller.createUser(expUser1);
		
		String login2 = "second";
		UserEntity expUser2 = new UserEntity(7, login2, "hash");
		controller.createUser(expUser2);
		
		String allUsers = controller.getAllUsers();
		assertThat(allUsers, containsString(login1));
		assertThat(allUsers, containsString(login2));
	}

}
