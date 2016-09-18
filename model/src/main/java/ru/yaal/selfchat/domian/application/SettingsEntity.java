package ru.yaal.selfchat.domian.application;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.yaal.selfchat.domian.DomainEntity;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SettingsEntity implements DomainEntity<SettingsEntity> {
	private Integer id;
	
	@Override
	public SettingsEntity withId(Integer id) {
		return new SettingsEntity(id);
	}
}
