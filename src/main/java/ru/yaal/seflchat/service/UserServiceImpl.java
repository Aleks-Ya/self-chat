package ru.yaal.seflchat.service;

import com.vaadin.server.VaadinSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaal.seflchat.data.User;
import ru.yaal.seflchat.repository.UserRepository;

import java.util.Optional;

import static ru.yaal.seflchat.vaadin.SessionListener.currentUserAttr;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Service
class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Autowired
    private UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User createUser(User newUser) {
        log.info("Create user: " + newUser);
        return repo.insert(newUser);
    }

    public User getCurrentUser() {
        return (User) VaadinSession.getCurrent().getAttribute(currentUserAttr);
    }

    @Override
    public void setCurrentUser(User user) {
        VaadinSession.getCurrent().setAttribute(currentUserAttr, user);
    }

    @Override
    public Optional<User> getUserByCookie(String cookieValue) {
        return repo.findByCookieValue(cookieValue);
    }
}
