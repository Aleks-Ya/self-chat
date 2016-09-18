package ru.yaal.selfchat.server.core.repository.impl.inmemory;

import org.springframework.stereotype.Component;

import ru.yaal.selfchat.domian.security.UserEntity;

@Component
class InMemoryUserRepository extends AbstractInMemoryRepository<UserEntity> {
}
