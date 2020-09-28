package poussecafe.shop.model.order;

import poussecafe.attribute.Attribute;
import poussecafe.discovery.Aggregate;
import poussecafe.discovery.MessageListener;
import poussecafe.discovery.ProducesEvent;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;
import poussecafe.domain.Factory;
import poussecafe.domain.Repository;
import poussecafe.shop.commands.SettleOrder;
import poussecafe.shop.commands.ShipOrder;
import poussecafe.shop.model.events.OrderCreated;
import poussecafe.shop.model.events.OrderPlaced;
import poussecafe.shop.model.events.OrderReadyForShipping;
import poussecafe.shop.model.events.OrderSettled;
import poussecafe.shop.process.OrderPlacement;
import poussecafe.shop.process.OrderSettlement;
import poussecafe.shop.process.OrderShippment;

@Aggregate
public class Order {

    public static class OrderFactory extends Factory<OrderId, OrderRoot, OrderRoot.Attributes> {

        @MessageListener(processes = OrderPlacement.class)
        public OrderRoot buildPlacedOrder(OrderPlaced event) {
            OrderDescription description = event.description().value();
            OrderId id = new OrderId(event.productId().value(), description.customerId(), description.reference());
            OrderRoot order = newAggregateWithId(id);
            order.attributes().units().value(description.units());
            return order;
        }
    }

    public static class OrderRoot extends AggregateRoot<OrderId, OrderRoot.Attributes> {

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

    public static class OrderRepository extends Repository<OrderRoot, OrderId, OrderRoot.Attributes> {

    }

    private Order() {

    }
}
