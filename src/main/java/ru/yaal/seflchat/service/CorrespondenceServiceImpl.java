package ru.yaal.seflchat.service;

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
    private final VaadinService vaadinService;
    private static final String attr = "currentCorrespondence";
    private final List<CorrespondenceListener> listeners = new ArrayList<>();

    @Autowired
    private CorrespondenceServiceImpl(CorrespondenceRepository repo, UserService userService, VaadinService vaadinService) {
        this.repo = repo;
        this.userService = userService;
        this.vaadinService = vaadinService;
    }

    private void eventListeners(Correspondence correspondence) {
        listeners.forEach(listener -> listener.correspondencehanged(correspondence));
    }

    public synchronized Correspondence getCurrentCorrespondence() {
        Correspondence correspondence = (Correspondence) vaadinService.getCurrentVaadinSession().getAttribute(attr);
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
        vaadinService.getCurrentVaadinSession().setAttribute(attr, correspondence);
        eventListeners(correspondence);
    }
}
