package poussecafe.shop.model.customer;

import poussecafe.discovery.Aggregate;
import poussecafe.discovery.MessageListener;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;
import poussecafe.domain.Factory;
import poussecafe.domain.Repository;
import poussecafe.shop.Shop;
import poussecafe.shop.commands.CreateCustomer;
import poussecafe.shop.process.CustomerCreation;

@Aggregate(module = Shop.class)
public class Customer {

    public static class CustomerFactory extends Factory<CustomerId, CustomerRoot, CustomerRoot.Attributes> {

        @MessageListener(processes = CustomerCreation.class)
        public CustomerRoot createCustomer(CreateCustomer command) {
            return newAggregateWithId(command.customerId().value());
        }
    }

    public static class CustomerRoot extends AggregateRoot<CustomerId, CustomerRoot.Attributes> {

        public static interface Attributes extends EntityAttributes<CustomerId> {

        }
    }

    public static class CustomerRepository extends Repository<CustomerRoot, CustomerId, CustomerRoot.Attributes> {

    }

    private Customer() {

    }
}
