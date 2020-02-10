package poussecafe.shop.domain.mongo;

import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.shop.adapters.storage.OrderIdData;

public interface OrderDataJpaRepository extends JpaRepository<OrderData, OrderIdData> {

}
