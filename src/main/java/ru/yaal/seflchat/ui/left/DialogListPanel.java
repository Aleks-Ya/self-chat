package ru.yaal.seflchat.ui.left;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
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

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
@UIScope
class DialogListPanel extends Panel implements EventService.DialogAddedListener,
        EventService.DialogRemovedListener, EventService.DialogRenamedListener, EventService.CorrespondenceSelectedListener {

    private final Table table = new Table();
    private final BeanItemContainer<Dialog> container = new BeanItemContainer<>(Dialog.class);

    @Autowired
    DialogListPanel(CorrespondenceService corService) {
        log.info("Create " + getClass().getSimpleName());

        table.setCaption("Dialogs");
        table.setContainerDataSource(container);
        table.setSizeFull();
        table.addItemClickListener(event -> {
            Dialog itemId = (Dialog) event.getItemId();
            corService.setCurrentDialog(itemId.getId());
        });
        table.addGeneratedColumn("Delete", new DeleteColumnGenerator(corService));

        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(table);
        setContent(vertical);
        setSizeFull();
    }

    private void update(Correspondence newCorrespondence) {
        container.removeAllItems();
        newCorrespondence.getUserDialogs().forEach(container::addBean);
        container.removeContainerProperty("id");
        container.removeContainerProperty("messages");
        table.setPageLength(table.size());
    }

    @Override
    public void dialogAdded(DialogEvent event) {
        update(event.getSelectedCorrespondence().get());
    }

    @Override
    public void dialogRenamed(DialogEvent event) {
        update(event.getSelectedCorrespondence().get());
    }

    @Override
    public void dialogRemoved(DialogEvent event) {
        update(event.getSelectedCorrespondence().get());
    }

    @Override
    public void correspondenceSelected(ru.yaal.seflchat.service.event.Event event) {
        update(event.getSelectedCorrespondence().get());
    }

    private class DeleteColumnGenerator implements Table.ColumnGenerator {
        private CorrespondenceService corService;

        DeleteColumnGenerator(CorrespondenceService corService) {
            this.corService = corService;
        }

        public com.vaadin.ui.Component generateCell(Table source, Object itemId, Object columnId) {
            return new Button("Delete", event -> {
                Dialog dialog = (Dialog) itemId;
                if (dialog.equals(corService.getCurrentDialog())) {
                    Dialog firstDialog = corService.getCurrentCorrespondence().getUserDialogs().get(0);
                    corService.setCurrentDialog(firstDialog.getId());
                }
                corService.removeDialog(dialog);
                source.markAsDirty();
            });
        }
    }
}
