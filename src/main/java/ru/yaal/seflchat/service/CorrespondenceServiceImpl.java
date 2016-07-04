package ru.yaal.seflchat.service;

import com.vaadin.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.repository.CorrespondenceRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Service
class CorrespondenceServiceImpl implements CorrespondenceService {
    private final CorrespondenceRepository repo;
    private final UserService userService;
    private static final String attr = "currentCorrespondence";
    private final List<CorrespondenceListener> listeners = new ArrayList<>();

    @Autowired
    private CorrespondenceServiceImpl(CorrespondenceRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    private void eventListeners(Correspondence correspondence) {
        listeners.forEach(listener -> listener.correspondencehanged(correspondence));
    }

    public synchronized Correspondence getCurrentCorrespondence() {
        Correspondence correspondence = (Correspondence) VaadinSession.getCurrent().getAttribute(attr);
        if (correspondence == null) {
            correspondence = repo.findByUser(userService.getCurrentUser())
                    .orElseGet(() -> createCorrespondence(new Correspondence(userService.getCurrentUser())));
            setCurrentCorrespondence(correspondence);
        }
        return correspondence;
    }

    @Override
    public Correspondence createCorrespondence(Correspondence correspondence) {
        return repo.insert(correspondence);
    }


    public synchronized void setCurrentCorrespondence(Correspondence correspondence) {
        VaadinSession.getCurrent().setAttribute(attr, correspondence);
        eventListeners(correspondence);
    }
}
