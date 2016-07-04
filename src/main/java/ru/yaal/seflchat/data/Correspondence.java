package ru.yaal.seflchat.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Getter
@Document
@EqualsAndHashCode
public class Correspondence {

    public Correspondence() {
    }

    public Correspondence(User user, List<Dialog> userDialogs) {
        this.user = user;
        this.userDialogs = userDialogs;
    }

    @Id
    private String id;
    @DBRef
    private User user;
    @DBRef
    private List<Dialog> userDialogs;

    public Correspondence(User user) {
        this.user = user;
        userDialogs = Collections.emptyList();
    }
}
