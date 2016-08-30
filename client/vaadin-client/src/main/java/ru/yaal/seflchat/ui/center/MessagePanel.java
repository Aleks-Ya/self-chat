package ru.yaal.seflchat.ui.center;

import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yaal.seflchat.data.Message;

/**
 * @author Yablokov Aleksey
 */
@Slf4j
@Component
@UIScope
class MessagePanel extends Panel implements Property<Message> {
    private final VerticalLayout vertical = new VerticalLayout();
    private final Label label = new Label();
    private Message value;

    MessagePanel() {
        log.info("Create " + getClass().getSimpleName());

        label.setContentMode(ContentMode.HTML);
        label.setReadOnly(true);
        label.setWidth(70, Unit.PERCENTAGE);
        vertical.addComponent(label);

        setContent(vertical);
        setSizeFull();
    }

    private void updateData() {
        setText(value.getContent(), value.getAlignment());
    }

    private void setText(String text, Message.Alignment alignment) {
        Alignment alignmentVaadin = alignment == Message.Alignment.RIGHT ? Alignment.TOP_RIGHT : Alignment.TOP_LEFT;
        vertical.setComponentAlignment(label, alignmentVaadin);

        String res = formatHtml(text, alignment);

        log.info("Formatted message text: \"{}\"", res);
        label.setValue(res);
    }

    private String formatHtml(String text, Message.Alignment alignment) {
        StringBuilder sb = new StringBuilder();
        for (String line : text.split("\\n")) {
            if (alignment == Message.Alignment.RIGHT) {
                sb.append("<p align='right'>");
            } else {
                sb.append("<p align='left'>");
            }
            sb.append(line).append("</p>");
        }
        return sb.toString();
    }

    @Override
    public Message getValue() {
        return value;
    }

    @Override
    public void setValue(Message newValue) throws ReadOnlyException {
        value = newValue;
        updateData();
    }

    @Override
    public Class<? extends Message> getType() {
        return Message.class;
    }
}
