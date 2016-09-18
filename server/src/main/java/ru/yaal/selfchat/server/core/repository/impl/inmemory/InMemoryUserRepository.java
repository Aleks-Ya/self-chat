package ru.yaal.selfchat.server.core.repository.impl.inmemory;

import org.springframework.stereotype.Component;

import ru.yaal.selfchat.domian.security.UserEntity;
import ru.yaal.selfchat.server.core.repository.UserRespository;

@Component
public class InMemoryUserRepository extends AbstractInMemoryRepository<UserEntity> implements UserRespository {
}
