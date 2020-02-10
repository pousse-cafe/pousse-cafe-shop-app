package poussecafe.shop.domain;

import poussecafe.attribute.Attribute;
import poussecafe.discovery.Aggregate;
import poussecafe.discovery.ProducesEvent;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;
import poussecafe.shop.Shop;

@Aggregate(
  factory = MessageFactory.class,
  repository = MessageRepository.class,
  module = Shop.class
)
public class Message extends AggregateRoot<MessageId, Message.Attributes> {

    @ProducesEvent(value = MessageCreated.class, consumedByExternal = "Communication System")
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
