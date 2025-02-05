package poussecafe.sample.process;

import org.junit.Test;
import poussecafe.sample.ShopTest;
import poussecafe.shop.core.commands.CreateCustomer;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.customer.Customer.Repository;
import poussecafe.shop.core.process.CustomerCreation;
import poussecafe.test.ProcessCovered;

import static org.junit.Assert.assertTrue;

@ProcessCovered(CustomerCreation.class)
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

    private Repository customerRepository;
}
