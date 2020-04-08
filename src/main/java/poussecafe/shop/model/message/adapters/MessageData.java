package poussecafe.shop.model.message.adapters;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.shop.model.message.ContentType;
import poussecafe.shop.model.message.Message;
import poussecafe.shop.model.message.MessageId;

@SuppressWarnings("serial")
@Entity
public class MessageData implements Message.Attributes, Serializable {

    @Override
    public Attribute<MessageId> identifier() {
        return AttributeBuilder.stringId(MessageId.class)
                .read(() -> id)
                .write(value -> id = value)
                .build();
    }

    @Id
    private String id;

    @Override
    public Attribute<CustomerId> customerId() {
        return AttributeBuilder.stringId(CustomerId.class)
                .read(() -> customerId)
                .write(value -> customerId = value)
                .build();
    }

    private String customerId;

    @Override
    public Attribute<ContentType> contentType() {
        return AttributeBuilder.single(ContentType.class)
                .read(() -> contentType)
                .write(value -> contentType = value)
                .build();
    }

    private ContentType contentType;
}
