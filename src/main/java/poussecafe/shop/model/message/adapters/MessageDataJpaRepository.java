package poussecafe.shop.model.message.adapters;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.shop.model.customer.CustomerId;

public interface MessageDataJpaRepository extends JpaRepository<MessageData, String> {

    List<MessageData> findByCustomerId(CustomerId customerId);
}
