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
        dialogs.add(dialog);
    }

    @Override
    public List<Dialog> getDialogs() {
        return dialogs;
    }

    protected void addMessage(Message message) {
        dialogs.get(0).addMessage(message);
    }
}
