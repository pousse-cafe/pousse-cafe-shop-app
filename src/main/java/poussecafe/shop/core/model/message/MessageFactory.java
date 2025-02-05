package poussecafe.shop.core.model.message;

import java.util.UUID;
import poussecafe.discovery.MessageListener;
import poussecafe.discovery.ProducesEvent;
import poussecafe.domain.AggregateFactory;
import poussecafe.shop.comm.events.CommunicationExpected;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.events.OrderCreated;
import poussecafe.shop.core.model.events.OrderReadyForShipping;
import poussecafe.shop.core.model.events.OrderRejected;
import poussecafe.shop.core.model.events.OrderSettled;
import poussecafe.shop.core.process.Messaging;

public class MessageFactory extends AggregateFactory<MessageId, MessageRoot, MessageRoot.Attributes> {

    /**
     * Creates a new Message upon Order rejection.
     */
    @MessageListener(processes = Messaging.class)
    @ProducesEvent(value = CommunicationExpected.class, consumedByExternal = "Communication")
    public MessageRoot buildMessage(OrderRejected event) {
        MessageRoot message = buildMessage(event.description().value().customerId());
        message.attributes().contentType().value(ContentType.ORDER_REJECTED);
        return message;
    }

    private MessageRoot buildMessage(CustomerId customerId) {
        MessageRoot message = newAggregateWithId(new MessageId(UUID.randomUUID().toString()));
        message.attributes().customerId().value(customerId);
        return message;
    }

    /**
     * Creates a new Message upon successful Order creation.
     */
    @MessageListener(processes = Messaging.class)
    @ProducesEvent(value = CommunicationExpected.class, consumedByExternal = "Communication")
    public MessageRoot buildMessage(OrderCreated event) {
        MessageRoot message = buildMessage(event.orderId().value().getCustomerId());
        message.attributes().contentType().value(ContentType.ORDER_READY_FOR_SETTLEMENT);
        return message;
    }

    /**
     * Creates a new Message upon Order settlement.
     */
    @MessageListener(processes = Messaging.class)
    @ProducesEvent(value = CommunicationExpected.class, consumedByExternal = "Communication")
    public MessageRoot buildMessage(OrderSettled event) {
        MessageRoot message = buildMessage(event.orderId().value().getCustomerId());
        message.attributes().contentType().value(ContentType.ORDER_SETTLED);
        return message;
    }

    /**
     * Creates a new Message upon Order shipment.
     */
    @MessageListener(processes = Messaging.class)
    @ProducesEvent(value = CommunicationExpected.class, consumedByExternal = "Communication")
    public MessageRoot buildMessage(OrderReadyForShipping event) {
        MessageRoot message = buildMessage(event.orderId().value().getCustomerId());
        message.attributes().contentType().value(ContentType.ORDER_READY_FOR_SHIPMENT);
        return message;
    }
}
