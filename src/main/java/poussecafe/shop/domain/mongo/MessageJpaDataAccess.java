package poussecafe.shop.domain.mongo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.domain.CustomerId;
import poussecafe.shop.domain.Message;
import poussecafe.shop.domain.MessageId;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
        aggregateRoot = Message.class,
        dataImplementation = MessageData.class,
        storageName = SpringJpaStorage.NAME
)
public class MessageJpaDataAccess extends JpaDataAccess<MessageId, MessageData, String> implements poussecafe.shop.domain.MessageDataAccess<MessageData> {

    @Autowired
    private MessageDataJpaRepository repository;

    @Override
    protected String convertId(MessageId id) {
        return id.stringValue();
    }

    @Override
    protected JpaRepository<MessageData, String> jpaRepository() {
        return repository;
    }

    @Override
    public List<MessageData> findByCustomer(CustomerId customerId) {
        return repository.findByCustomerId(customerId);
    }

}
