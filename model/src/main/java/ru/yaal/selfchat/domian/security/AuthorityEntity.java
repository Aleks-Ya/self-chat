package ru.yaal.selfchat.domian.security;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthorityEntity {
	private Integer id;
	private String name;
	
	public AuthorityEntity withId(Integer id) {
		return new AuthorityEntity(id, this.name);
	}

	public AuthorityEntity withName(String name) {
		return new AuthorityEntity(this.id, name);
	}
}
