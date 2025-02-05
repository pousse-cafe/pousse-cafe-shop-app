package poussecafe.shop.core.model.message.adapters;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.message.ContentType;
import poussecafe.shop.core.model.message.MessageId;
import poussecafe.shop.core.model.message.MessageRoot;

@SuppressWarnings("serial")
@Entity
public class MessageAttributes implements MessageRoot.Attributes, Serializable {

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
