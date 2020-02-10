package poussecafe.shop.domain.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.adapters.storage.OrderIdData;
import poussecafe.shop.domain.Order;
import poussecafe.shop.domain.OrderId;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
        aggregateRoot = Order.class,
        dataImplementation = OrderData.class,
        storageName = SpringJpaStorage.NAME
)
public class OrderJpaDataAccess extends JpaDataAccess<OrderId, OrderData, OrderIdData> implements poussecafe.shop.domain.OrderDataAccess<OrderData> {

    @Autowired
    private OrderDataJpaRepository repository;

    @Override
    protected OrderIdData convertId(OrderId id) {
        return OrderIdData.adapt(id);
    }

    @Override
    protected JpaRepository<OrderData, OrderIdData> jpaRepository() {
        return repository;
    }

}
