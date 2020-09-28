package poussecafe.shop.model.product;

import poussecafe.attribute.Attribute;
import poussecafe.discovery.Aggregate;
import poussecafe.discovery.MessageListener;
import poussecafe.discovery.ProducesEvent;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.DomainException;
import poussecafe.domain.EntityAttributes;
import poussecafe.shop.commands.AddUnits;
import poussecafe.shop.commands.PlaceOrder;
import poussecafe.shop.model.events.OrderPlaced;
import poussecafe.shop.model.events.OrderRejected;
import poussecafe.shop.model.order.OrderDescription;
import poussecafe.shop.process.OrderPlacement;
import poussecafe.shop.process.ProductManagement;

@Aggregate(
  factory = ProductFactory.class,
  repository = ProductRepository.class
)
public class Product extends AggregateRoot<ProductId, Product.Attributes> {

    @MessageListener(runner = AddUnitsRunner.class, processes = ProductManagement.class)
    public void addUnits(AddUnits command) {
        int units = command.units().value();
        if(units <= 0) {
            throw new DomainException("Cannot add negative number of units");
        }
        attributes().availableUnits().value(attributes().availableUnits().value() + units);
        attributes().totalUnits().value(attributes().totalUnits().value() + units);
    }

    @MessageListener(runner = PlaceOrderRunner.class, processes = OrderPlacement.class)
    @ProducesEvent(value = OrderRejected.class, required = false)
    @ProducesEvent(value = OrderPlaced.class, required = false)
    public void placeOrder(PlaceOrder command) {
        int unitsAvailable = attributes().availableUnits().value();
        OrderDescription description = command.description().value();
        if (description.units() > unitsAvailable) {
            OrderRejected event = newDomainEvent(OrderRejected.class);
            event.productId().valueOf(attributes().identifier());
            event.description().value(description);
            issue(event);
        } else {
            attributes().availableUnits().value(unitsAvailable - description.units());

            OrderPlaced event = newDomainEvent(OrderPlaced.class);
            event.productId().valueOf(attributes().identifier());
            event.description().value(description);
            issue(event);
        }
    }

    public static interface Attributes extends EntityAttributes<ProductId> {

        Attribute<Integer> totalUnits();

        Attribute<Integer> availableUnits();
    }

}
