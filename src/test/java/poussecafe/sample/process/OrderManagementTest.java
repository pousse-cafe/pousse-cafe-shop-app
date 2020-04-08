package poussecafe.sample.process;

import org.junit.Test;
import poussecafe.sample.ShopTest;
import poussecafe.shop.commands.PlaceOrder;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.shop.model.order.Order;
import poussecafe.shop.model.order.OrderDescription;
import poussecafe.shop.model.order.OrderId;
import poussecafe.shop.model.product.ProductId;

import static org.junit.Assert.assertTrue;

public class OrderManagementTest extends ShopTest {

    private CustomerId customerId;

    private ProductId productId;

    private OrderDescription description;

    @Test
    public void placingOrderCreatesOrder() {
        givenCustomer();
        givenProductWithUnits(true);
        whenPlacingOrder();
        thenOrderCreated();
    }

    private void givenCustomer() {
        customerId = new CustomerId("customer-id");
    }

    private void givenProductWithUnits(boolean withUnits) {
        productId = new ProductId("product-1");
        if (withUnits) {
            loadDataFile("/data/placingOrderProductWithUnits.json");
        } else {
            loadDataFile("/data/placingOrderProductWithoutUnits.json");
        }
    }

    private void whenPlacingOrder() {
        PlaceOrder command = newCommand(PlaceOrder.class);
        command.productId().value(productId);
        description = new OrderDescription.Builder()
                .customerId(customerId)
                .reference("ref")
                .units(1)
                .build();
        command.description().value(description);
        submitCommand(command);
    }

    private void thenOrderCreated() {
        assertTrue(getOptional(Order.class, orderId()).isPresent());
    }

    private OrderId orderId() {
        return new OrderId(productId, description.customerId(), description.reference());
    }

    @Test
    public void placingOrderWithNotEnoughUnitsDoesNotCreatesOrder() {
        givenCustomer();
        givenProductWithUnits(false);
        whenPlacingOrder();
        thenNoOrderCreated();
    }

    private void thenNoOrderCreated() {
        assertTrue(getOptional(Order.class, orderId()).isEmpty());
    }
}
