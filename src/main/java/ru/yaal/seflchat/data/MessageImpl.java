package ru.yaal.seflchat.data;

/**
 * @author Yablokov Aleksey
 */
public class MessageImpl implements Message {
    private String content;

    public MessageImpl(String content) {
        this.content = content;
    }

    @Override
    public String getMessage() {
        return content;
    }
}
