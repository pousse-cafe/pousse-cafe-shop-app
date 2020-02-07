package poussecafe.shop.domain;

import poussecafe.discovery.MessageListener;
import poussecafe.domain.Factory;
import poussecafe.shop.command.CreateCustomer;
import poussecafe.shop.process.CustomerCreation;

public class CustomerFactory extends Factory<CustomerId, Customer, Customer.Attributes> {

    @MessageListener(processes = CustomerCreation.class)
    public Customer createCustomer(CreateCustomer command) {
        return newAggregateWithId(command.customerId().value());
    }
}
