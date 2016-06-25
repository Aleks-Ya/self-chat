package ru.yaal.seflchat.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public class BeanDataSource implements DataSource {
    private final List<Dialog> dialogs = new ArrayList<>();

    public BeanDataSource() {
        Dialog dialog = new DialogImpl();
        dialog.addMessage(new MessageImpl("Hello! My name is Petr."));
        dialog.addMessage(new MessageImpl("Nice to meet you!"));
        dialogs.add(dialog);
    }

    @Override
    public List<Dialog> getDialogs() {
        return dialogs;
    }
}
