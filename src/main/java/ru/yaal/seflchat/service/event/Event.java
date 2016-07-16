package ru.yaal.seflchat.service.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;

import java.util.function.Supplier;

/**
 * @author Aleksey Yablokov
 */
@Getter
@AllArgsConstructor
public class Event {
    Supplier<Correspondence> selectedCorrespondence;
    Supplier<Dialog> selectedDialog;
}
