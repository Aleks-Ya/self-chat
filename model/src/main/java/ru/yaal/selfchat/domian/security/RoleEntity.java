package ru.yaal.selfchat.domian.security;

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
public class RoleEntity implements DomainEntity {
	private Integer id;
	private String name;
	private List<AuthorityEntity> authorities;
	
	public RoleEntity withId(Integer id) {
		return new RoleEntity(id, this.name, this.authorities);
	}

	public RoleEntity withName(String name) {
		return new RoleEntity(this.id, name, this.authorities);
	}
}
