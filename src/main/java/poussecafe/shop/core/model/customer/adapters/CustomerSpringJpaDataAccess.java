package poussecafe.shop.core.model.customer.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.core.model.customer.CustomerDataAccess;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.customer.Customer.Root;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
    aggregateRoot = Root.class,
    dataImplementation = CustomerAttributes.class,
    storageName = SpringJpaStorage.NAME
)
public class CustomerSpringJpaDataAccess
extends JpaDataAccess<CustomerId, CustomerAttributes, String>
implements CustomerDataAccess<CustomerAttributes> {

    @Autowired
    private CustomerAttributesJpaRepository repository;

    @Override
    protected String convertId(CustomerId id) {
        return id.stringValue();
    }

    @Override
    protected JpaRepository<CustomerAttributes, String> jpaRepository() {
        return repository;
    }
}
