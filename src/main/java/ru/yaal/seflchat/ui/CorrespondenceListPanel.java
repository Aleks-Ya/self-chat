package ru.yaal.seflchat.ui;

import com.vaadin.data.Property;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.service.correspondence.CorrespondenceService;
import ru.yaal.seflchat.service.dialog.CurrentDialogService;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
class CorrespondenceListPanel extends Panel
        implements Property<Correspondence>, CorrespondenceService.CorrespondenceListener, CurrentDialogService.CurrentDialogListener {

    private Correspondence value;
    private final Table table = new Table();

    CorrespondenceListPanel() {
        log.info("Create " + getClass().getSimpleName());
        table.addContainerProperty("Dialogs", String.class, null);
        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(table);
        setContent(vertical);
        setSizeFull();
        table.setSizeFull();
    }

    private void update() {
        table.removeAllItems();
        List<Dialog> dialogs = value.getUserDialogs();
        for (int i = 0; i < dialogs.size(); i++) {
            Object[] item = new Object[]{dialogs.get(i).getName()};
            table.addItem(item, i);
        }
        table.setPageLength(table.size());
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

    @Override
    public void correspondenceChanged(Correspondence correspondence) {
        update();
    }

    @Override
    public void dialogChanged(Dialog dialog) {
        update();
    }
}