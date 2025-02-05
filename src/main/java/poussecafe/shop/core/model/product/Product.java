package poussecafe.shop.core.model.product;

import poussecafe.attribute.Attribute;
import poussecafe.discovery.Aggregate;
import poussecafe.discovery.MessageListener;
import poussecafe.discovery.ProducesEvent;
import poussecafe.domain.AggregateFactory;
import poussecafe.domain.AggregateRepository;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.DomainException;
import poussecafe.domain.EntityAttributes;
import poussecafe.shop.core.commands.AddUnits;
import poussecafe.shop.core.commands.CreateProduct;
import poussecafe.shop.core.commands.PlaceOrder;
import poussecafe.shop.core.model.events.OrderPlaced;
import poussecafe.shop.core.model.events.OrderRejected;
import poussecafe.shop.core.model.order.OrderDescription;
import poussecafe.shop.core.model.product.Product.Root.Attributes;
import poussecafe.shop.core.process.OrderPlacement;
import poussecafe.shop.core.process.ProductManagement;

/**
 * <p>A Product is a good or service that can be bought by a Customer. Customer have to place an Order when buying units
 * of a given Product. The number of available units may be increased and is decreased with successfully placed Orders.</p>
 */
@Aggregate
public class Product {

    public static class Factory extends AggregateFactory<ProductId, Root, Root.Attributes> {

        /**
         * Creates a new Product with no stock (i.e. 0 units available).
         */
        @MessageListener(processes = ProductManagement.class)
        public Root buildProductWithNoStock(CreateProduct command) {
            Root product = newAggregateWithId(command.productId().value());
            product.attributes().totalUnits().value(0);
            product.attributes().availableUnits().value(0);
            return product;
        }
    }

    public static class Root extends AggregateRoot<ProductId, Root.Attributes> {

        /**
         * Adds available units to the Product.
         */
        @MessageListener(runner = AddUnitsRunner.class, processes = ProductManagement.class)
        public void addUnits(AddUnits command) {
            int units = command.units().value();
            if(units <= 0) {
                throw new DomainException("Cannot add negative number of units");
            }
            attributes().availableUnits().value(attributes().availableUnits().value() + units);
            attributes().totalUnits().value(attributes().totalUnits().value() + units);
        }

        /**
         * Tries to place an order if there are enough units available.
         */
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

    public static class Repository extends AggregateRepository<ProductId, Root, Root.Attributes> {

        @Override
        public ProductDataAccess<Root.Attributes> dataAccess() {
            return (ProductDataAccess<Attributes>) super.dataAccess();
        }
    }

    private Product() {

    }
}
