package poussecafe.shop.model.customer;

import poussecafe.discovery.Aggregate;
import poussecafe.discovery.MessageListener;
import poussecafe.domain.AggregateFactory;
import poussecafe.domain.AggregateRepository;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;
import poussecafe.shop.commands.CreateCustomer;
import poussecafe.shop.process.CustomerCreation;

/**
 * <p>A Customer can place Orders and receive Messages.</p>
 */
@Aggregate
public class Customer {

    public static class Factory extends AggregateFactory<CustomerId, Root, Root.Attributes> {

        @MessageListener(processes = CustomerCreation.class)
        public Root createCustomer(CreateCustomer command) {
            return newAggregateWithId(command.customerId().value());
        }
    }

    public static class Root extends AggregateRoot<CustomerId, Root.Attributes> {

        public static interface Attributes extends EntityAttributes<CustomerId> {

        }
    }

    public static class Repository extends AggregateRepository<CustomerId, Root, Root.Attributes> {

    }

    private Customer() {

    }
}