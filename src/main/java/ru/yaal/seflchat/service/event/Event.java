package ru.yaal.seflchat.service.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;

/**
 * @author Aleksey Yablokov
 */
@Getter
@AllArgsConstructor
public class Event {
    Correspondence selectedCorrespondence;
    Dialog selectedDialog;
}
