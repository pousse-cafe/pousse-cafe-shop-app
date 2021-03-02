package poussecafe.shop.model.order;

import poussecafe.attribute.Attribute;
import poussecafe.discovery.Aggregate;
import poussecafe.discovery.MessageListener;
import poussecafe.discovery.ProducesEvent;
import poussecafe.domain.AggregateFactory;
import poussecafe.domain.AggregateRepository;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;
import poussecafe.shop.commands.SettleOrder;
import poussecafe.shop.commands.ShipOrder;
import poussecafe.shop.model.events.OrderCreated;
import poussecafe.shop.model.events.OrderPlaced;
import poussecafe.shop.model.events.OrderReadyForShipping;
import poussecafe.shop.model.events.OrderSettled;
import poussecafe.shop.process.OrderPlacement;
import poussecafe.shop.process.OrderSettlement;
import poussecafe.shop.process.OrderShippment;

/**
 * <p>Orders are placed by Customers when they buy a given number of units of a given Product. An Order is first created,
 * then settled (upon receivable of Customer's payment), and finally shipped (when passed over to transporter).</p>
 */
@Aggregate
public class Order {

    public static class Factory extends AggregateFactory<OrderId, Root, Root.Attributes> {

        /**
         * Creates an Order is it was successfully placed.
         */
        @MessageListener(processes = OrderPlacement.class)
        @ProducesEvent(OrderCreated.class)
        public Root buildPlacedOrder(OrderPlaced event) {
            OrderDescription description = event.description().value();
            OrderId id = new OrderId(event.productId().value(), description.customerId(), description.reference());
            Root order = newAggregateWithId(id);
            order.attributes().units().value(description.units());
            return order;
        }
    }

    public static class Root extends AggregateRoot<OrderId, Root.Attributes> {

        @Override
        public void onAdd() {
            OrderCreated event = newDomainEvent(OrderCreated.class);
            event.orderId().valueOf(attributes().identifier());
            issue(event);
        }

        /**
         * Settles the Order.
         */
        @MessageListener(runner = SettleRunner.class, processes = OrderSettlement.class)
        @ProducesEvent(OrderSettled.class)
        public void settle(SettleOrder command) {
            OrderSettled event = newDomainEvent(OrderSettled.class);
            event.orderId().valueOf(attributes().identifier());
            issue(event);
        }

        /**
         * Marks the Order as ready to be shipped.
         */
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

    public static class Repository extends AggregateRepository<OrderId, Root, Root.Attributes> {

    }

    private Order() {

    }
}
