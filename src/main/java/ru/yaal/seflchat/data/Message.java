package ru.yaal.seflchat.data;

import org.springframework.data.annotation.Id;

/**
 * @author Yablokov Aleksey
 */
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
