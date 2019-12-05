package poussecafe.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import poussecafe.runtime.Runtime;
import poussecafe.shop.command.CreateCustomer;
import poussecafe.shop.domain.CustomerId;
import poussecafe.shop.domain.CustomerRepository;
import poussecafe.shop.view.CreateCustomerView;
import poussecafe.shop.view.CustomerView;

@RestController
public class RestCustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Runtime runtime;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping(path = "/customer")
    public void createCustomer(@RequestBody CreateCustomerView input) {
        logger.info("Creating customer with id {}", input.id);
        CustomerId customerId = new CustomerId(input.id);
        CreateCustomer command = runtime.newCommand(CreateCustomer.class);
        command.customerId().value(customerId);
        runtime.submitCommand(command);
    }

    @GetMapping(path = "/customer/{id}")
    public CustomerView getCustomer(@PathVariable("id") String id) {
        logger.info("Fetching customer with id {}", id);
        CustomerId customerId = new CustomerId(id);
        customerRepository.get(customerId);
        CustomerView view = new CustomerView();
        view.id = id;
        return view;
    }
}
