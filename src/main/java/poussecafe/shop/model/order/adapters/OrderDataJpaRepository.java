package poussecafe.shop.model.order.adapters;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDataJpaRepository extends JpaRepository<OrderData, OrderIdData> {

}
