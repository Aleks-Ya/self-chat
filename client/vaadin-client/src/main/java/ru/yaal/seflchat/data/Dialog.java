package ru.yaal.seflchat.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Data
@Document
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

    @Id
    private String id;

    private String name = "no_name";

    private List<Message> messages = new ArrayList<>();
}
