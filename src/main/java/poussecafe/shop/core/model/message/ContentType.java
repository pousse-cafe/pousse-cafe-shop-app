package poussecafe.shop.core.model.message;

/**
 * The type of message to send.
 */
public enum ContentType {
    ORDER_REJECTED,
    ORDER_READY_FOR_SETTLEMENT,
    ORDER_SETTLED,
    ORDER_READY_FOR_SHIPMENT,
    PRODUCT_CREATED
}
