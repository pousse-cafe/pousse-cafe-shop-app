package poussecafe.shop.domain;

import poussecafe.discovery.MessageListener;
import poussecafe.domain.Factory;
import poussecafe.shop.command.CreateProduct;
import poussecafe.shop.process.ProductManagement;

public class ProductFactory extends Factory<ProductId, Product, Product.Attributes> {

    @MessageListener(processes = ProductManagement.class)
    public Product buildProductWithNoStock(CreateProduct command) {
        Product product = newAggregateWithId(command.productId().value());
        product.attributes().totalUnits().value(0);
        product.attributes().availableUnits().value(0);
        return product;
    }
}
