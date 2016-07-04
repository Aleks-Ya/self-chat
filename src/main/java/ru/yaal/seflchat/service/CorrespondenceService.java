package ru.yaal.seflchat.service;

import ru.yaal.seflchat.data.Correspondence;

/**
 * @author Yablokov Aleksey
 */
public interface CorrespondenceService {

    Correspondence getCurrentCorrespondence();

    Correspondence createCorrespondence(Correspondence correspondence);

    interface CorrespondenceListener {
        void correspondencehanged(Correspondence correspondence);
    }
}
