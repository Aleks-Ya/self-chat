package ru.yaal.seflchat.service;

import org.junit.Ignore;
import org.junit.Test;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.data.Message;
import ru.yaal.seflchat.spring.mongo.BaseMongoTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyIterable.emptyIterable;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class CurrentDialogServiceTest extends BaseMongoTest {
    @Test
    public void getCurrentUserDialogs() {
    }

    @Test
    public void getCurrentDialog() {
        assertNotNull(cds.getCurrentDialog());
    }

    @Test
    public void addMessageToCurrentDialog() {
        assertThat(cds.getCurrentDialog().getMessages(), emptyIterable());
        Message message = new Message("aa", Message.Alignment.LEFT);
        cds.addMessageToCurrentDialog(message);
        assertThat(cds.getCurrentDialog().getMessages(), contains(message));
    }

    @Test
    public void addListener() {

    }

    @Test
    @Ignore("Save dialog before find it")
    public void setCurrentDialog() {
        Dialog old = cds.getCurrentDialog();
        Dialog exp = new Dialog("test dialog");
        cds.setCurrentDialog(exp.getId());
        assertThat(cds.getCurrentDialog(), equalTo(exp));
        assertThat(cds.getCurrentDialog(), not(equalTo(old)));
    }

    @Test
    public void getNextMessageAlignment() {

    }

    @Test
    public void clearCurrentDialog() {
        cds.addMessageToCurrentDialog(new Message(null, null));
        cds.addMessageToCurrentDialog(new Message(null, null));
        assertThat(cds.getCurrentDialog().getMessages(), hasSize(2));
        cds.clearCurrentDialog();
        assertThat(cds.getCurrentDialog().getMessages(), emptyIterable());
    }

    @Test
    public void fireCurrentDialogChanged() {

    }

    @Test
    public void renameCurrentDialog() {
        assertNotNull(cds.getCurrentDialog());

        String newName = "new dialog name";
        cds.renameCurrentDialog(newName);

        Dialog currentFromSession = cds.getCurrentDialog();
        assertThat(currentFromSession.getName(), equalTo(newName));

        Dialog actFromDb = dRepo.findOne(currentFromSession.getId());
        assertThat(actFromDb.getName(), equalTo(newName));
    }
}