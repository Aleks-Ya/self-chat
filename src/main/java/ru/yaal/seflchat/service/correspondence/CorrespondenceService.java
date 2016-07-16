package ru.yaal.seflchat.service.correspondence;

import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;

/**
 * @author Yablokov Aleksey
 */
public interface CorrespondenceService {

    Correspondence getCurrentCorrespondence();

    Correspondence createCorrespondence(Correspondence correspondence);

    void addDialog(Dialog dialog);

    void addListener(CorrespondenceListener listener);

    interface CorrespondenceListener {
        void correspondenceChanged(Correspondence correspondence);
    }

    void removeDialog(Dialog dialog);
}
