package ru.yaal.seflchat.service;

import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinSession;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.spring.SpringProfiles;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(SpringProfiles.TEST)
class TestVaadinService implements VaadinService {
    @Override
    @SneakyThrows
    public VaadinSession getCurrentVaadinSession() {
        return new TestVaadinSession();
    }

    private static class TestVaadinSession extends VaadinSession {
        private Map<String, Object> attrs = new HashMap<>();

        TestVaadinSession() throws ServiceException {
            this(null);
        }

        private TestVaadinSession(com.vaadin.server.VaadinService service) {
            super(service);
        }

        @Override
        public Object getAttribute(String name) {
            return attrs.get(name);
        }

        @Override
        public void setAttribute(String name, Object value) {
            attrs.put(name, value);
        }
    }
}
