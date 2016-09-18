package ru.yaal.selfchat.domian.security;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.yaal.selfchat.domian.DomainEntity;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserEntity implements DomainEntity<UserEntity> {
	private Integer id;
	private String login;
	private String passwordHash;
	private List<RoleEntity> roles;
	
	public UserEntity(Integer id, String login, String passwordHash) {
		this(id, login, passwordHash, Collections.emptyList());
	}

	@Override
	public UserEntity withId(Integer id) {
		return new UserEntity(id, this.login, this.passwordHash, Collections.emptyList());
	}

	public UserEntity withLogin(String login) {
		return new UserEntity(this.id, login, this.passwordHash, Collections.emptyList());
	}
}
