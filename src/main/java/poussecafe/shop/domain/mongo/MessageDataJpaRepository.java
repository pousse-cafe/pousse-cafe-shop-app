package poussecafe.shop.domain.mongo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.shop.domain.CustomerId;

public interface MessageDataJpaRepository extends JpaRepository<MessageData, String> {

    List<MessageData> findByCustomerId(CustomerId customerId);
}
