package ru.yaal.selfchat.server.data;

import lombok.Getter;

@Getter
public class UserEntity {
	private int id;
	private String login;
	private String passwordHash;
}
