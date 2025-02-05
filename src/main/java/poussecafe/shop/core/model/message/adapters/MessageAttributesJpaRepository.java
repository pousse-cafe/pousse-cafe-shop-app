package poussecafe.shop.core.model.message.adapters;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import poussecafe.shop.core.model.customer.CustomerId;

public interface MessageAttributesJpaRepository extends JpaRepository<MessageAttributes, String> {

    List<MessageAttributes> findByCustomerId(CustomerId customerId);
}
