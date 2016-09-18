package ru.yaal.selfchat.server.core.repository;

import java.util.List;

public interface Crud<E> {
	E create(E entity);

	E get(int id);

	List<E> getAll();
	
	E update(E entity);

	boolean delete(int id);
}
