package ru.yaal.seflchat.data;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public class Dialog {

    public Dialog() {
    }

    public Dialog(String userId) {
        this.userId = userId;
    }

    private Dialog(String id, String userId, List<Message> messages) {
        this.id = id;
        this.userId = userId;
        this.messages = messages;
    }

    @Id
    private String id;

    private String userId;

    public String getId() {
        return id;
    }

    private List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Dialog withAddMessage(Message message) {
        ArrayList<Message> messages = new ArrayList<>(this.messages);
        messages.add(message);
        return new Dialog(id, userId, messages);
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", messages=" + messages.size() +
                '}';
    }
}
