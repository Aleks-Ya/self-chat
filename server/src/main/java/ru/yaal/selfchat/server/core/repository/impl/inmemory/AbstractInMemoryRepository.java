package ru.yaal.selfchat.server.core.repository.impl.inmemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.yaal.selfchat.domian.DomainEntity;
import ru.yaal.selfchat.server.core.repository.Crud;

abstract class AbstractInMemoryRepository<E extends DomainEntity<E>> implements Crud<E> {

	private final Map<Integer, E> entities = new HashMap<>();
	private int nextId = 0;

	@Override
	public E create(E entity) {
		if (entity.getId() == null) {
			entity = entity.withId(nextId);
			nextId++;
			entities.put(entity.getId(), entity);
		} else {
			if (entities.containsKey(entity.getId())) {
				throw new IllegalArgumentException("Entity already exists: " + entity.getId());
			} else {
				entities.put(entity.getId(), entity);
			}
		}
		return entity;
	}

	@Override
	public E get(int userId) {
		return entities.get(userId);
	}

	@Override
	public List<E> getAll() {
		return new ArrayList<>(entities.values());
	}

	@Override
	public E update(E entity) {
		assert entity.getId() != null;
		assert entities.containsKey(entity.getId());
		return entities.put(entity.getId(), entity);
	}

	@Override
	public boolean delete(int id) {
		return entities.remove(id) != null;
	}

}
