package ru.yaal.seflchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.repository.CorrespondenceRepository;
import ru.yaal.seflchat.repository.DialogRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Service
class CorrespondenceServiceImpl implements CorrespondenceService {
    private final CorrespondenceRepository cRepo;
    private final UserService userService;
    private final DialogRepository dRepo;
    private final VaadinService vaadinService;
    private final List<CorrespondenceListener> listeners = new ArrayList<>();

    @Autowired
    private CorrespondenceServiceImpl(CorrespondenceRepository cRepo, UserService userService,
                                      DialogRepository dRepo, VaadinService vaadinService) {
        this.cRepo = cRepo;
        this.userService = userService;
        this.dRepo = dRepo;
        this.vaadinService = vaadinService;
    }

    private void eventListeners(Correspondence correspondence) {
        listeners.forEach(listener -> listener.correspondenceChanged(correspondence));
    }

    public synchronized Correspondence getCurrentCorrespondence() {
        Correspondence correspondence = vaadinService.getCorrespondenceFromSession();
        if (correspondence == null) {
            correspondence = cRepo.findByUser(userService.getCurrentUser())
                    .orElseGet(() -> createCorrespondence(new Correspondence(userService.getCurrentUser())));
            setCurrentCorrespondence(correspondence);
        }
        return correspondence;
    }

    @Override
    public Correspondence createCorrespondence(Correspondence correspondence) {
        return cRepo.insert(correspondence);
    }

    @Override
    public void addDialog(Dialog dialog) {
        dRepo.save(dialog);
        Correspondence correspondence = getCurrentCorrespondence();
        correspondence.getUserDialogs().add(dialog);
        cRepo.save(correspondence);
        eventListeners(correspondence);
    }

    public synchronized void setCurrentCorrespondence(Correspondence correspondence) {
        vaadinService.setCorrespondenceToSession(correspondence);
        eventListeners(correspondence);
    }

    @Override
    public void addListener(CorrespondenceListener listener) {
        listeners.add(listener);
    }
}
