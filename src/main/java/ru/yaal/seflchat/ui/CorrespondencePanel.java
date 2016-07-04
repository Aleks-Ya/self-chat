package ru.yaal.seflchat.ui;

import com.vaadin.data.Property;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;

/**
 * @author Yablokov Aleksey
 */
@Component
@Slf4j
class CorrespondencePanel extends Panel implements Property<Correspondence> {
    private Correspondence value;
    private final Table table = new Table();

    CorrespondencePanel() {
        log.info("Create " + getClass().getSimpleName());
        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(table);
        setScrollTop(9999);
        setContent(vertical);
        setSizeFull();
    }

    private void update() {
        table.removeAllItems();
        value.getUserDialogs().stream().map(Dialog::getName).forEach(table::addItem);
    }

    @Override
    public Correspondence getValue() {
        return value;
    }

    @Override
    public void setValue(Correspondence newValue) throws ReadOnlyException {
        value = newValue;
        update();
    }

    @Override
    public Class<? extends Correspondence> getType() {
        return Correspondence.class;
    }
}
