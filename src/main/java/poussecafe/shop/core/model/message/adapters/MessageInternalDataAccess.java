package poussecafe.shop.core.model.message.adapters;

import java.util.List;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.message.MessageDataAccess;
import poussecafe.shop.core.model.message.MessageId;
import poussecafe.shop.core.model.message.MessageRoot;
import poussecafe.storage.internal.InternalDataAccess;
import poussecafe.storage.internal.InternalStorage;

import static java.util.Arrays.asList;

@DataAccessImplementation(
    aggregateRoot = MessageRoot.class,
    dataImplementation = MessageAttributes.class,
    storageName = InternalStorage.NAME
)
public class MessageInternalDataAccess extends InternalDataAccess<MessageId, MessageAttributes> implements MessageDataAccess<MessageAttributes> {

    @Override
    protected List<Object> extractIndexedData(MessageAttributes data) {
        return asList(data.customerId().value());
    }

    @Override
    public List<MessageAttributes> findByCustomer(CustomerId customerId) {
        return findBy(customerId);
    }

}
