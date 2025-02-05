package poussecafe.shop.core.model.message;

import poussecafe.attribute.Attribute;
import poussecafe.discovery.Aggregate;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;
import poussecafe.shop.comm.events.CommunicationExpected;
import poussecafe.shop.comm.model.CommunicationId;
import poussecafe.shop.core.model.customer.CustomerId;

/**
 * <p>Messages are sent to Customers to notify them about an event.</p>
 */
@Aggregate(
  factory = MessageFactory.class,
  repository = MessageRepository.class
)
public class MessageRoot extends AggregateRoot<MessageId, MessageRoot.Attributes> {

    @Override
    public void onAdd() {
        CommunicationExpected event = newDomainEvent(CommunicationExpected.class);
        event.messageId().value(new CommunicationId(attributes().identifier().value().stringValue()));
        issue(event);
    }

    public static interface Attributes extends EntityAttributes<MessageId> {

        Attribute<CustomerId> customerId();

        Attribute<ContentType> contentType();
    }
}