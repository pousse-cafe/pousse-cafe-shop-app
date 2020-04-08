package poussecafe.shop.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import poussecafe.runtime.Runtime;
import poussecafe.shop.api.view.OrderView;
import poussecafe.shop.api.view.PlaceOrderView;
import poussecafe.shop.api.view.SettleOrderView;
import poussecafe.shop.api.view.ShipOrderView;
import poussecafe.shop.commands.PlaceOrder;
import poussecafe.shop.commands.SettleOrder;
import poussecafe.shop.commands.ShipOrder;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.shop.model.order.Order;
import poussecafe.shop.model.order.OrderDescription;
import poussecafe.shop.model.order.OrderId;
import poussecafe.shop.model.order.OrderRepository;
import poussecafe.shop.model.product.ProductId;

@RestController
public class OrderController {

    @GetMapping(path = "/orders/{customerId}/{reference}/{productId}")
    public OrderView getOrder(@PathVariable("customerId") String customerId, @PathVariable("reference") String reference, @PathVariable("productId") String productId) {
        OrderId orderId = new OrderId(new ProductId(productId), new CustomerId(customerId), reference);
        Order order = orderRepository.get(orderId);
        OrderView orderView = new OrderView();
        orderView.customerId = customerId;
        orderView.reference = reference;
        orderView.productId = productId;
        orderView.units = order.attributes().units().value();
        return orderView;
    }

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping(path = "/placed-orders")
    public void placeOrder(@RequestBody PlaceOrderView input) {
        logger.info("Placing order for customer id {}, with product id {}, reference {}, unit {}", input.customerId, input.productId, input.reference, input.units);
        ProductId productId = new ProductId(input.productId);
        OrderDescription orderDescription = new OrderDescription.Builder()
                .customerId(new CustomerId(input.customerId))
                .reference(input.reference)
                .units(input.units)
                .build();
        PlaceOrder command = runtime.newCommand(PlaceOrder.class);
        command.productId().value(productId);
        command.description().value(orderDescription);
        runtime.submitCommand(command);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Runtime runtime;

    @PostMapping(path = "/settled-orders")
    public void settleOrder(@RequestBody SettleOrderView input) {
        logger.info("Settling order for customer id {}, with product id {}, reference {}", input.customerId, input.productId, input.reference);
        OrderId orderId = new OrderId(new ProductId(input.productId), new CustomerId(input.customerId), input.reference);
        SettleOrder command = runtime.newCommand(SettleOrder.class);
        command.orderId().value(orderId);
        runtime.submitCommand(command);
    }

    @PostMapping(path = "/shipped-orders")
    public void shipOrder(@RequestBody ShipOrderView input) {
        logger.info("Shipping order for customer id {}, with product id {}, reference {}", input.customerId, input.productId, input.reference);
        OrderId orderId = new OrderId(new ProductId(input.productId), new CustomerId(input.customerId), input.reference);
        ShipOrder command = runtime.newCommand(ShipOrder.class);
        command.orderId().value(orderId);
        runtime.submitCommand(command);
    }
}
