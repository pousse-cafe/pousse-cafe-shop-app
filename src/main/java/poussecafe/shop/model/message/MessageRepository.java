package poussecafe.shop.model.message;

import java.util.List;
import poussecafe.domain.AggregateRepository;
import poussecafe.shop.model.customer.CustomerId;

public class MessageRepository extends AggregateRepository<MessageId, Message, Message.Attributes> {

    public List<Message> findByCustomer(CustomerId customerId) {
        return wrap(dataAccess().findByCustomer(customerId));
    }

    @Override
    public MessageDataAccess<Message.Attributes> dataAccess() {
        return (MessageDataAccess<Message.Attributes>) super.dataAccess();
    }
}
