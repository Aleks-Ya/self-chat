package ru.yaal.seflchat.data;

import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author Yablokov Aleksey
 */
@ToString
public class Message {
    @Id
    private String id;
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
