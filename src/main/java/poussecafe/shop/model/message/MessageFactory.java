package poussecafe.shop.model.message;

import java.util.UUID;
import poussecafe.discovery.MessageListener;
import poussecafe.domain.AggregateFactory;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.shop.model.events.OrderCreated;
import poussecafe.shop.model.events.OrderReadyForShipping;
import poussecafe.shop.model.events.OrderRejected;
import poussecafe.shop.model.events.OrderSettled;
import poussecafe.shop.process.Messaging;

public class MessageFactory extends AggregateFactory<MessageId, MessageRoot, MessageRoot.Attributes> {

    @MessageListener(processes = Messaging.class)
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

    @MessageListener(processes = Messaging.class)
    public MessageRoot buildMessage(OrderCreated event) {
        MessageRoot message = buildMessage(event.orderId().value().getCustomerId());
        message.attributes().contentType().value(ContentType.ORDER_READY_FOR_SETTLEMENT);
        return message;
    }

    @MessageListener(processes = Messaging.class)
    public MessageRoot buildMessage(OrderSettled event) {
        MessageRoot message = buildMessage(event.orderId().value().getCustomerId());
        message.attributes().contentType().value(ContentType.ORDER_SETTLED);
        return message;
    }

    @MessageListener(processes = Messaging.class)
    public MessageRoot buildMessage(OrderReadyForShipping event) {
        MessageRoot message = buildMessage(event.orderId().value().getCustomerId());
        message.attributes().contentType().value(ContentType.ORDER_READY_FOR_SHIPMENT);
        return message;
    }
}
