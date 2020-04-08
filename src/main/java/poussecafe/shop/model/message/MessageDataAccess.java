package poussecafe.shop.model.message;

import java.util.List;
import poussecafe.domain.EntityDataAccess;
import poussecafe.shop.model.customer.CustomerId;

public interface MessageDataAccess<N extends Message.Attributes> extends EntityDataAccess<MessageId, N> {

    List<N> findByCustomer(CustomerId customerId);

}
