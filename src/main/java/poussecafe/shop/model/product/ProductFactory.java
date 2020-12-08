package poussecafe.shop.model.product;

import poussecafe.discovery.MessageListener;
import poussecafe.domain.AggregateFactory;
import poussecafe.shop.commands.CreateProduct;
import poussecafe.shop.process.ProductManagement;

public class ProductFactory extends AggregateFactory<ProductId, Product, Product.Attributes> {

    @MessageListener(processes = ProductManagement.class)
    public Product buildProductWithNoStock(CreateProduct command) {
        Product product = newAggregateWithId(command.productId().value());
        product.attributes().totalUnits().value(0);
        product.attributes().availableUnits().value(0);
        return product;
    }
}
