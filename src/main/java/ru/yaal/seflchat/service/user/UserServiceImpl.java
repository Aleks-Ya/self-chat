package ru.yaal.seflchat.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaal.seflchat.data.User;
import ru.yaal.seflchat.repository.UserRepository;
import ru.yaal.seflchat.service.vaadin.VaadinService;

import javax.servlet.http.Cookie;
import java.time.Instant;
import java.util.Optional;

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
        String userId = vaadinService.getUserIdFromSession();
        return userId != null ? repo.findOne(userId) : null;
    }

    @Override
    public void setCurrentUser(User user) {
        vaadinService.setUserIdToSession(user.getId());
    }

    @Override
    public Optional<User> getUserByCookie(String cookieValue) {
        return repo.findByCookieValue(cookieValue);
    }

    @Override
    public void initCurrentUser() {
        User user = takeUser(vaadinService.getUserCookie());
        setCurrentUser(user);
    }

    private User takeUser(Cookie cookie) {
        if (cookie != null) {
            Optional<User> user = getUserByCookie(cookie.getValue());
            return user.orElseGet(() -> createNewUser(cookie.getValue()));
        } else {
            String value = Instant.now().toString();
            vaadinService.setUserCookie(value);
            return createNewUser(value);
        }
    }

    private User createNewUser(String value) {
        return createUser(new User(null, "user_" + Instant.now(), "abcd", value));
    }

}
