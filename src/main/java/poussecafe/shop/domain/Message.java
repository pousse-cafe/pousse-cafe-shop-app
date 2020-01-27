package poussecafe.shop.domain;

import poussecafe.attribute.Attribute;
import poussecafe.discovery.Aggregate;
import poussecafe.discovery.ProducesEvent;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;

@Aggregate(
  factory = MessageFactory.class,
  repository = MessageRepository.class
)
public class Message extends AggregateRoot<MessageId, Message.Attributes> {

    /**
     * @process Messaging
     */
    @ProducesEvent(MessageCreated.class)
    @Override
    public void onAdd() {
        MessageCreated event = newDomainEvent(MessageCreated.class);
        event.messageId().valueOf(attributes().identifier());
        emitDomainEvent(event);
    }

    public static interface Attributes extends EntityAttributes<MessageId> {

        Attribute<CustomerId> customerId();

        Attribute<ContentType> contentType();
    }

}
