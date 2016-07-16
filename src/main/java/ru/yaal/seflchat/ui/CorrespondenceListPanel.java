package ru.yaal.seflchat.ui;

import com.vaadin.data.Property;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CorrespondenceListPanel(CurrentDialogService dialogService) {
        log.info("Create " + getClass().getSimpleName());
        table.addContainerProperty("Dialogs", String.class, null);
        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(table);
        setContent(vertical);
        setSizeFull();
        table.setSizeFull();
        table.addItemClickListener(event -> dialogService.setCurrentDialog(event.getItemId().toString()));
    }

    private void update() {
        table.removeAllItems();
        List<Dialog> dialogs = value.getUserDialogs();
        for (Dialog dialog : dialogs) {
            Object[] item = new Object[]{dialog.getName()};
            table.addItem(item, dialog.getId());
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
