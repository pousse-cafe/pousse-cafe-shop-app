package poussecafe.shop.model.events;

import poussecafe.attribute.Attribute;
import poussecafe.domain.DomainEvent;
import poussecafe.shop.model.message.MessageId;

public interface MessageCreated extends DomainEvent {

    Attribute<MessageId> messageId();
}
