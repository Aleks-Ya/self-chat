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
public class MessageEntity implements DomainEntity<MessageEntity> {
	private Integer id;
	private String content;
	private Alignment alignment;
	
	@Override
	public MessageEntity withId(Integer id) {
		return new MessageEntity(id, this.content, this.alignment);
	}
	
	public enum Alignment {
		LEFT, RIGHT
	}
}
