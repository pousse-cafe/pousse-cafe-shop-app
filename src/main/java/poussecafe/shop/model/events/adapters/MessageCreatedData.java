package poussecafe.shop.model.events.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.model.events.MessageCreated;
import poussecafe.shop.model.message.MessageId;

@MessageImplementation(message = MessageCreated.class)
@SuppressWarnings("serial")
public class MessageCreatedData implements Serializable, MessageCreated {

    @Override
    public Attribute<MessageId> messageId() {
        return AttributeBuilder.single(MessageId.class)
                .storedAs(String.class)
                .adaptOnRead(MessageId::new)
                .read(() -> messageId)
                .adaptOnWrite(MessageId::stringValue)
                .write(value -> messageId = value)
                .build();
    }

    private String messageId;
}
