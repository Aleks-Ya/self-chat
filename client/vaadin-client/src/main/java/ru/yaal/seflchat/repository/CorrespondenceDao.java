package ru.yaal.seflchat.repository;

import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.User;

import java.util.Optional;

/**
 * @author Aleksey Yablokov
 */
public interface CorrespondenceDao {
    Optional<Correspondence> findByUser(User user);

    Correspondence save(Correspondence correspondence);

    Correspondence insert(Correspondence correspondence);

    Correspondence findOne(String correspondenceId);
}
