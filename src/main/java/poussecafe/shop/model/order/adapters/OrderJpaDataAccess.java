package poussecafe.shop.model.order.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.model.order.Order.OrderRoot;
import poussecafe.shop.model.order.OrderId;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
        aggregateRoot = OrderRoot.class,
        dataImplementation = OrderData.class,
        storageName = SpringJpaStorage.NAME
)
public class OrderJpaDataAccess extends JpaDataAccess<OrderId, OrderData, OrderIdData> implements poussecafe.shop.model.order.OrderDataAccess<OrderData> {

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
