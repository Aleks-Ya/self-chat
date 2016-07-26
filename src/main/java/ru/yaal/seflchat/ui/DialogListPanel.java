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
import ru.yaal.seflchat.service.event.DialogEvent;
import ru.yaal.seflchat.service.event.EventService;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
class DialogListPanel extends Panel
        implements Property<Correspondence>, EventService.DialogAddedListener, EventService.DialogRemovedListener,
        EventService.DialogRenamedListener {

    private final Table table = new Table();
    private Correspondence value;

    @Autowired
    DialogListPanel(CorrespondenceService corService) {
        log.info("Create " + getClass().getSimpleName());
        table.addContainerProperty("Dialogs", String.class, null);
        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(table);
        setContent(vertical);
        setSizeFull();
        table.setSizeFull();
        table.addItemClickListener(event -> corService.setCurrentDialog(event.getItemId().toString()));
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
    public void dialogAdded(DialogEvent event) {
        setValue(event.getSelectedCorrespondence().get());
    }

    @Override
    public void dialogRenamed(DialogEvent event) {
        setValue(event.getSelectedCorrespondence().get());
    }

    @Override
    public void dialogRemoved(DialogEvent event) {
        setValue(event.getSelectedCorrespondence().get());
    }
}
