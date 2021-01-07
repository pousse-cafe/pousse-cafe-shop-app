package poussecafe.shop.model.message;

import poussecafe.attribute.Attribute;
import poussecafe.discovery.Aggregate;
import poussecafe.discovery.ProducesEvent;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.shop.model.events.MessageCreated;

@Aggregate(
  factory = MessageFactory.class,
  repository = MessageRepository.class
)
public class MessageRoot extends AggregateRoot<MessageId, MessageRoot.Attributes> {

    @ProducesEvent(value = MessageCreated.class, consumedByExternal = "Communication System")
    @Override
    public void onAdd() {
        MessageCreated event = newDomainEvent(MessageCreated.class);
        event.messageId().valueOf(attributes().identifier());
        issue(event);
    }

    public static interface Attributes extends EntityAttributes<MessageId> {

        Attribute<CustomerId> customerId();

        Attribute<ContentType> contentType();
    }
}