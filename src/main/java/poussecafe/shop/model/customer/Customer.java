package poussecafe.shop.model.customer;

import poussecafe.discovery.Aggregate;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;
import poussecafe.shop.Shop;

@Aggregate(
    factory = CustomerFactory.class,
    repository = CustomerRepository.class,
    module = Shop.class
)
public class Customer extends AggregateRoot<CustomerId, Customer.Attributes> {

    public static interface Attributes extends EntityAttributes<CustomerId> {

    }

}
