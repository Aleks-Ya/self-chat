package ru.yaal.seflchat.data;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public interface DataSource {
    List<Dialog> getDialogs();
}
