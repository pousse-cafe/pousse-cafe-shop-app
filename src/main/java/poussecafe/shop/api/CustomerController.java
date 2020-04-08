package poussecafe.shop.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import poussecafe.runtime.Runtime;
import poussecafe.shop.api.view.CreateCustomerView;
import poussecafe.shop.api.view.CustomerView;
import poussecafe.shop.commands.CreateCustomer;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.shop.model.customer.CustomerRepository;

@RestController
public class CustomerController {

    @PostMapping(path = "/customer")
    public void createCustomer(@RequestBody CreateCustomerView input) {
        logger.info("Creating customer with id {}", input.id);
        CustomerId customerId = new CustomerId(input.id);
        CreateCustomer command = runtime.newCommand(CreateCustomer.class);
        command.customerId().value(customerId);
        runtime.submitCommand(command);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Runtime runtime;

    @GetMapping(path = "/customer/{id}")
    public CustomerView getCustomer(@PathVariable("id") String id) {
        logger.info("Fetching customer with id {}", id);
        CustomerId customerId = new CustomerId(id);
        customerRepository.get(customerId);
        CustomerView view = new CustomerView();
        view.id = id;
        return view;
    }

    @Autowired
    private CustomerRepository customerRepository;
}
