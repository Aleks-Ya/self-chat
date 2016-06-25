package ru.yaal.seflchat.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public class DialogImpl implements Dialog {
    private List<MessageImpl> messages = new ArrayList<>();

    @Override
    public List<MessageImpl> getMessages() {
        return messages;
    }

    @Override
    public void addMessage(MessageImpl message) {
        messages.add(message);
    }
}
