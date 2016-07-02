package ru.yaal.seflchat.data;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author Yablokov Aleksey
 */
@ToString
@Getter
public class Message {
    @Id
    private String id;
    private String content;
    private Alignment alignment;

    public Message(String content, Alignment alignment) {
        this.content = content;
        this.alignment = alignment;
    }

    /**
     * @author Yablokov Aleksey
     */
    public enum Alignment {
        LEFT, RIGHT
    }
}
