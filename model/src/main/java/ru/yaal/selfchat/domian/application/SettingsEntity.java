package ru.yaal.selfchat.domian.application;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SettingsEntity {
	private Integer id;
	
	public SettingsEntity withId(Integer id) {
		return new SettingsEntity(id);
	}
}
