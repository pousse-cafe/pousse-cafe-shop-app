package poussecafe.shop.core.model.order.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.core.model.order.OrderId;
import poussecafe.shop.core.model.order.Order.Root;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
        aggregateRoot = Root.class,
        dataImplementation = OrderAttributes.class,
        storageName = SpringJpaStorage.NAME
)
public class OrderSpringJpaDataAccess extends JpaDataAccess<OrderId, OrderAttributes, OrderIdData> implements poussecafe.shop.core.model.order.OrderDataAccess<OrderAttributes> {

    @Autowired
    private OrderAttributesJpaRepository repository;

    @Override
    protected OrderIdData convertId(OrderId id) {
        return OrderIdData.adapt(id);
    }

    @Override
    protected JpaRepository<OrderAttributes, OrderIdData> jpaRepository() {
        return repository;
    }

}
