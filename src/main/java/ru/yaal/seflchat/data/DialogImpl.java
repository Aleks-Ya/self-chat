package ru.yaal.seflchat.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
class DialogImpl implements Dialog {
    private List<Message> messages = new ArrayList<>();

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addMessage(Message message) {
        messages.add(message);
    }
}
