package poussecafe.sample.process;

import org.junit.Test;
import poussecafe.sample.ShopTest;
import poussecafe.shop.commands.CreateCustomer;
import poussecafe.shop.model.customer.Customer.CustomerRepository;
import poussecafe.shop.model.customer.CustomerId;

import static org.junit.Assert.assertTrue;

public class CustomerCreationTest extends ShopTest {

    private CustomerId customerId;

    @Test
    public void customerCreation() {
        givenCustomerId();
        whenCreatingCustomer();
        thenCustomerIsCreated();
    }

    private void givenCustomerId() {
        customerId = new CustomerId("customer-id");
    }

    private void whenCreatingCustomer() {
        CreateCustomer command = newCommand(CreateCustomer.class);
        command.customerId().value(customerId);
        submitCommand(command);
    }

    private void thenCustomerIsCreated() {
        assertTrue(customerRepository.getOptional(customerId).isPresent());
    }

    private CustomerRepository customerRepository;
}
