package ru.yaal.seflchat.data;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public interface Dialog {
    List<MessageImpl> getMessages();

    void addMessage(MessageImpl message);
}
