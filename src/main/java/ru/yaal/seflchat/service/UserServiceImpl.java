package ru.yaal.seflchat.service;

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
    private final VaadinService vaadinService;

    @Autowired
    private UserServiceImpl(UserRepository repo, VaadinService vaadinService) {
        this.repo = repo;
        this.vaadinService = vaadinService;
    }

    @Override
    public User createUser(User newUser) {
        log.info("Create user: " + newUser);
        return repo.insert(newUser);
    }

    public User getCurrentUser() {
        return (User) vaadinService.getCurrentVaadinSession().getAttribute(currentUserAttr);
    }

    @Override
    public void setCurrentUser(User user) {
        vaadinService.getCurrentVaadinSession().setAttribute(currentUserAttr, user);
    }

    @Override
    public Optional<User> getUserByCookie(String cookieValue) {
        return repo.findByCookieValue(cookieValue);
    }
}
