package ru.yaal.seflchat.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Yablokov Aleksey
 */
@ToString
@Getter
@EqualsAndHashCode
public class Message {
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
