package ru.yaal.selfchat.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserEntity {
	private Integer id;
	private String login;
	private String passwordHash;
	
	public UserEntity withId(Integer id) {
		return new UserEntity(id, this.login, this.passwordHash);
	}

	public UserEntity withLogin(String login) {
		return new UserEntity(this.id, login, this.passwordHash);
	}
}
