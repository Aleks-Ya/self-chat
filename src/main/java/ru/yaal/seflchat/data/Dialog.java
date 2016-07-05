package ru.yaal.seflchat.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
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
@ToString
public class Dialog {

    /**
     * For mongo
     */
    @SuppressWarnings("unused")
    private Dialog() {
    }

    public Dialog(String name) {
        this.name = name;
    }

    private Dialog(String id, String name, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.messages = messages;
    }

    @Id
    private String id;

    private String name = "no_name";

    private List<Message> messages = new ArrayList<>();

    public Dialog withAddMessage(Message message) {
        ArrayList<Message> messages = new ArrayList<>(this.messages);
        messages.add(message);
        return new Dialog(id, name, Collections.unmodifiableList(messages));
    }

    public Dialog withClearMessages() {
        return new Dialog(id, name, Collections.emptyList());
    }
}
