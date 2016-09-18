package ru.yaal.selfchat.domian.business;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.yaal.selfchat.domian.DomainEntity;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MessageEntity implements DomainEntity {
	private Integer id;
	private String content;
	private Alignment alignment;
	
	public MessageEntity withId(Integer id) {
		return new MessageEntity(id, this.content, this.alignment);
	}
	
	public enum Alignment {
		LEFT, RIGHT
	}
}
