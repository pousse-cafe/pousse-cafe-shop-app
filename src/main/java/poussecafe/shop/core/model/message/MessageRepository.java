package poussecafe.shop.core.model.message;

import java.util.List;
import poussecafe.domain.AggregateRepository;
import poussecafe.shop.core.model.customer.CustomerId;

public class MessageRepository extends AggregateRepository<MessageId, MessageRoot, MessageRoot.Attributes> {

    public List<MessageRoot> findByCustomer(CustomerId customerId) {
        return wrap(dataAccess().findByCustomer(customerId));
    }

    @Override
    public MessageDataAccess<MessageRoot.Attributes> dataAccess() {
        return (MessageDataAccess<MessageRoot.Attributes>) super.dataAccess();
    }
}
