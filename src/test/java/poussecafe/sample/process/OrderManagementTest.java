package poussecafe.sample.process;

import org.junit.Test;
import poussecafe.sample.ShopTest;
import poussecafe.shop.core.commands.PlaceOrder;
import poussecafe.shop.core.model.customer.CustomerId;
import poussecafe.shop.core.model.order.OrderDescription;
import poussecafe.shop.core.model.order.OrderId;
import poussecafe.shop.core.model.order.Order.Repository;
import poussecafe.shop.core.model.product.Product;
import poussecafe.shop.core.model.product.ProductId;
import poussecafe.shop.core.model.product.adapters.ProductAttributes;
import poussecafe.shop.core.process.OrderPlacement;
import poussecafe.test.DataSet;
import poussecafe.test.ProcessCovered;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ProcessCovered(OrderPlacement.class)
public class OrderManagementTest extends ShopTest {

    @Test
    public void placingOrderCreatesOrder() {
        given(productWithUnits(true));
        givenPlaceOrderCommand();
        when(placeOrder);
        thenOrderCreated(true);
    }

    private DataSet productWithUnits(boolean withUnits) {
        var builder = new DataSet.Builder();
        if (withUnits) {
            builder.withAggregateData(Product.Root.class, productAttributesWithUnits(10, 10));
        } else {
            builder.withAggregateData(Product.Root.class, productAttributesWithUnits(10, 0));
        }
        return builder.build();
    }

    private ProductAttributes productAttributesWithUnits(int total, int available) {
        var attributes = new ProductAttributes();
        attributes.identifier().value(productId);
        attributes.totalUnits().value(total);
        attributes.availableUnits().value(available);
        return attributes;
    }

    private ProductId productId = new ProductId("product-1");

    private void givenPlaceOrderCommand() {
        placeOrder = newCommand(PlaceOrder.class);
        placeOrder.productId().value(productId);
        description = new OrderDescription.Builder()
                .customerId(customerId)
                .reference("ref")
                .units(1)
                .build();
        placeOrder.description().value(description);
    }

    private OrderDescription description;

    private CustomerId customerId = new CustomerId("customer-id");

    private PlaceOrder placeOrder;

    private void thenOrderCreated(boolean expected) {
        var description = placeOrder.description().value();
        var orderId = new OrderId(productId, customerId, description.reference());
        if(expected) {
            assertTrue(orderRepository.getOptional(orderId).isPresent());
        } else {
            assertFalse(orderRepository.getOptional(orderId).isPresent());
        }
    }

    private Repository orderRepository;

    @Test
    public void placingOrderWithNotEnoughUnitsDoesNotCreatesOrder() {
        given(productWithUnits(false));
        givenPlaceOrderCommand();
        when(placeOrder);
        thenOrderCreated(false);
    }
}
