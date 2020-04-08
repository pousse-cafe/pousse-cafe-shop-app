package poussecafe.shop.model.order;

import poussecafe.attribute.Attribute;
import poussecafe.discovery.Aggregate;
import poussecafe.discovery.MessageListener;
import poussecafe.discovery.ProducesEvent;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;
import poussecafe.shop.Shop;
import poussecafe.shop.commands.SettleOrder;
import poussecafe.shop.commands.ShipOrder;
import poussecafe.shop.model.events.OrderCreated;
import poussecafe.shop.model.events.OrderReadyForShipping;
import poussecafe.shop.model.events.OrderSettled;
import poussecafe.shop.process.OrderSettlement;
import poussecafe.shop.process.OrderShippment;

@Aggregate(
  factory = OrderFactory.class,
  repository = OrderRepository.class,
  module = Shop.class
)
public class Order extends AggregateRoot<OrderId, Order.Attributes> {

    @ProducesEvent(OrderCreated.class)
    @Override
    public void onAdd() {
        OrderCreated event = newDomainEvent(OrderCreated.class);
        event.orderId().valueOf(attributes().identifier());
        issue(event);
    }

    @MessageListener(runner = SettleRunner.class, processes = OrderSettlement.class)
    @ProducesEvent(OrderSettled.class)
    public void settle(SettleOrder command) {
        OrderSettled event = newDomainEvent(OrderSettled.class);
        event.orderId().valueOf(attributes().identifier());
        issue(event);
    }

    @MessageListener(runner = ShipOrderRunner.class, processes = OrderShippment.class)
    @ProducesEvent(OrderReadyForShipping.class)
    public void ship(ShipOrder command) {
        OrderReadyForShipping event = newDomainEvent(OrderReadyForShipping.class);
        event.orderId().valueOf(attributes().identifier());
        issue(event);
    }

    public static interface Attributes extends EntityAttributes<OrderId> {

        Attribute<Integer> units();
    }
}
