package poussecafe.shop.model.product.adapters;

import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.model.product.Product;
import poussecafe.shop.model.product.ProductId;
import poussecafe.storage.internal.InternalDataAccess;
import poussecafe.storage.internal.InternalStorage;

@DataAccessImplementation(
    aggregateRoot = Product.class,
    dataImplementation = ProductData.class,
    storageName = InternalStorage.NAME
)
public class ProductInternalDataAccess extends InternalDataAccess<ProductId, Product.Attributes> {

}
