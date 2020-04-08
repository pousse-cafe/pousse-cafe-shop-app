package poussecafe.shop.model.customer.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.model.customer.Customer;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
        aggregateRoot = Customer.class,
        dataImplementation = CustomerData.class,
        storageName = SpringJpaStorage.NAME
)
public class CustomerJpaDataAccess extends JpaDataAccess<CustomerId, CustomerData, String> implements poussecafe.shop.model.customer.CustomerDataAccess<CustomerData> {

    @Autowired
    private CustomerDataJpaRepository repository;

    @Override
    protected String convertId(CustomerId id) {
        return id.stringValue();
    }

    @Override
    protected JpaRepository<CustomerData, String> jpaRepository() {
        return repository;
    }
}
