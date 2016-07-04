package ru.yaal.seflchat.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Getter
@Document
@EqualsAndHashCode
public class Dialog {

    public Dialog() {
    }

    private Dialog(String id, List<Message> messages) {
        this.id = id;
        this.messages = messages;
    }

    @Id
    private String id;

    private String name;

    private List<Message> messages = new ArrayList<>();

    public Dialog withAddMessage(Message message) {
        ArrayList<Message> messages = new ArrayList<>(this.messages);
        messages.add(message);
        return new Dialog(id, Collections.unmodifiableList(messages));
    }

    public Dialog withClearMessages() {
        return new Dialog(id, Collections.emptyList());
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "id='" + id + '\'' +
                ", messages=" + messages.size() +
                '}';
    }
}
