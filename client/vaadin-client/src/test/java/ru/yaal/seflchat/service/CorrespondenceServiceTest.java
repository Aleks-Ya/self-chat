package ru.yaal.seflchat.service;

import org.junit.Test;
import ru.yaal.seflchat.data.Correspondence;
import ru.yaal.seflchat.data.Dialog;
import ru.yaal.seflchat.spring.mongo.BaseMongoTest;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

/**
 * @author Yablokov Aleksey
 */
public class CorrespondenceServiceTest extends BaseMongoTest {
    @Test
    public void getCurrentCorrespondence() {
        assertNotNull(cs.getCurrentCorrespondence());
    }

    @Test
    public void createCorrespondence() {
        Correspondence c = new Correspondence();
        cs.createCorrespondence(c);
        assertThat(cRepo.findOne(c.getId()), equalTo(c));
    }

    @Test
    public void addDialog() {
        Dialog dialog = new Dialog("name");
        cs.addDialog(dialog);
        assertThat(cs.getCurrentCorrespondence().getUserDialogs(), hasItem(dialog));
    }

    @Test
    public void updateDialog() {
        Dialog dialog = new Dialog("old dialog");
        cs.addDialog(dialog);
        cs.getCurrentCorrespondence();
    }
}