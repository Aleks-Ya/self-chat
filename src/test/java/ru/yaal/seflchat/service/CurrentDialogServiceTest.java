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
        assertNotNull(cs.getCurrentDialog());
    }

    @Test
    public void addMessageToCurrentDialog() {
        assertThat(cs.getCurrentDialog().getMessages(), emptyIterable());
        Message message = new Message("aa", Message.Alignment.LEFT);
        cs.addMessageToCurrentDialog(message);
        assertThat(cs.getCurrentDialog().getMessages(), contains(message));
    }

    @Test
    public void addListener() {

    }

    @Test
    @Ignore("Save dialog before find it")
    public void setCurrentDialog() {
        Dialog old = cs.getCurrentDialog();
        Dialog exp = new Dialog("test dialog");
        cs.setCurrentDialog(exp.getId());
        assertThat(cs.getCurrentDialog(), equalTo(exp));
        assertThat(cs.getCurrentDialog(), not(equalTo(old)));
    }

    @Test
    public void getNextMessageAlignment() {

    }

    @Test
    public void clearCurrentDialog() {
        cs.addMessageToCurrentDialog(new Message(null, null));
        cs.addMessageToCurrentDialog(new Message(null, null));
        assertThat(cs.getCurrentDialog().getMessages(), hasSize(2));
        cs.clearCurrentDialog();
        assertThat(cs.getCurrentDialog().getMessages(), emptyIterable());
    }

    @Test
    public void fireCurrentDialogChanged() {

    }

    @Test
    public void renameCurrentDialog() {
        assertNotNull(cs.getCurrentDialog());

        String newName = "new dialog name";
        cs.renameCurrentDialog(newName);

        Dialog currentFromSession = cs.getCurrentDialog();
        assertThat(currentFromSession.getName(), equalTo(newName));

        Dialog actFromDb = dRepo.findOne(currentFromSession.getId());
        assertThat(actFromDb.getName(), equalTo(newName));
    }
}