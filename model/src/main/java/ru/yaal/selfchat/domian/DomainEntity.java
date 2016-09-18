package ru.yaal.selfchat.domian;

public interface DomainEntity<E> {
	Integer getId();

	E withId(Integer id);
}
