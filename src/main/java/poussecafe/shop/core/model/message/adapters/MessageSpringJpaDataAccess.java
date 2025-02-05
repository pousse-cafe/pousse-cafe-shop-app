package poussecafe.shop.core.model.message.adapters;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.message.MessageId;
import poussecafe.shop.core.model.message.MessageRoot;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
        aggregateRoot = MessageRoot.class,
        dataImplementation = MessageAttributes.class,
        storageName = SpringJpaStorage.NAME
)
public class MessageSpringJpaDataAccess extends JpaDataAccess<MessageId, MessageAttributes, String> implements poussecafe.shop.core.model.message.MessageDataAccess<MessageAttributes> {

    @Autowired
    private MessageAttributesJpaRepository repository;

    @Override
    protected String convertId(MessageId id) {
        return id.stringValue();
    }

    @Override
    protected JpaRepository<MessageAttributes, String> jpaRepository() {
        return repository;
    }

    @Override
    public List<MessageAttributes> findByCustomer(CustomerId customerId) {
        return repository.findByCustomerId(customerId);
    }

}
