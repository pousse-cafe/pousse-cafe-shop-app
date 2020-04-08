package poussecafe.shop.model.message.adapters;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.shop.model.message.Message;
import poussecafe.shop.model.message.MessageId;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
        aggregateRoot = Message.class,
        dataImplementation = MessageData.class,
        storageName = SpringJpaStorage.NAME
)
public class MessageJpaDataAccess extends JpaDataAccess<MessageId, MessageData, String> implements poussecafe.shop.model.message.MessageDataAccess<MessageData> {

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
