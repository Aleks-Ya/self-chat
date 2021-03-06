package ru.yaal.selfchat.domian.business;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.yaal.selfchat.domian.DomainEntity;
import ru.yaal.selfchat.domian.security.UserEntity;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CorrespondenceEntity implements DomainEntity<CorrespondenceEntity>{
	private Integer id;
	private UserEntity user;
	private List<DialogEntity> roles;

	@Override
	public CorrespondenceEntity withId(Integer id) {
		return new CorrespondenceEntity(id, this.user, this.roles);
	}
}
