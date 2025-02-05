package poussecafe.sample.process;

import java.util.List;
import org.junit.Test;
import poussecafe.sample.ShopTest;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.events.OrderRejected;
import poussecafe.shop.core.model.events.adapters.OrderRejectedData;
import poussecafe.shop.core.model.message.ContentType;
import poussecafe.shop.core.model.message.MessageRepository;
import poussecafe.shop.core.model.message.MessageRoot;
import poussecafe.shop.core.model.order.OrderDescription;
import poussecafe.shop.core.model.product.ProductId;
import poussecafe.shop.core.process.Messaging;
import poussecafe.test.ProcessCovered;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ProcessCovered(Messaging.class)
public class MessagingTest extends ShopTest {

    private CustomerId customerId;

    private ProductId productId;

    private OrderDescription orderDescription;

    @Test
    public void rejectedOrderTriggersNotification() {
        givenOrder();
        whenOrderRejected();
        thenMessageCreatedWithContent(ContentType.ORDER_REJECTED);
    }

    private void givenOrder() {
        customerId = new CustomerId("customer-id");
        productId = new ProductId("product-id");
        orderDescription = new OrderDescription.Builder()
                .customerId(customerId)
                .reference("ref")
                .units(1)
                .build();
    }

    private void whenOrderRejected() {
        OrderRejected event = new OrderRejectedData();
        event.productId().value(productId);
        event.description().value(orderDescription);
        issue(event);
    }

    private void thenMessageCreatedWithContent(ContentType contentType) {
        waitUntilAllMessageQueuesEmpty();
        List<MessageRoot> messages = messageRepository.findByCustomer(customerId);
        assertThat(messages.size(), is(1));
        assertThat(messages.get(0).attributes().contentType().value(), is(contentType));
    }

    private MessageRepository messageRepository;
}
