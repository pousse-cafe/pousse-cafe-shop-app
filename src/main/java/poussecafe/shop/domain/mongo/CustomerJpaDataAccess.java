package poussecafe.shop.domain.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.domain.Customer;
import poussecafe.shop.domain.CustomerId;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
        aggregateRoot = Customer.class,
        dataImplementation = CustomerData.class,
        storageName = SpringJpaStorage.NAME
)
public class CustomerJpaDataAccess extends JpaDataAccess<CustomerId, CustomerData, String> implements poussecafe.shop.domain.CustomerDataAccess<CustomerData> {

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
