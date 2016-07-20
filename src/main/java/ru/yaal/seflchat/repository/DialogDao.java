package ru.yaal.seflchat.repository;

import ru.yaal.seflchat.data.Dialog;

/**
 * @author Aleksey Yablokov
 */
public interface DialogDao {
    Dialog save(Dialog dialog);

    Dialog insert(Dialog dialog);

    Dialog findOne(String dialogId);

    void delete(Dialog dialog);
}
