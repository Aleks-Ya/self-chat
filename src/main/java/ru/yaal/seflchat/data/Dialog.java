package ru.yaal.seflchat.data;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public interface Dialog {
    List<Message> getMessages();

    void addMessage(Message message);
}
