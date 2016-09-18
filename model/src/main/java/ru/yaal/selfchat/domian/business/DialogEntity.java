package ru.yaal.selfchat.domian.business;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DialogEntity {
	private Integer id;
	private String name;
	private List<MessageEntity> messages;
	
	public DialogEntity withId(Integer id) {
		return new DialogEntity(id, this.name, this.messages);
	}
}
