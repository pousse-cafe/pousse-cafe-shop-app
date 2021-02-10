package poussecafe.shop.model.product.adapters;

import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.model.product.Product;
import poussecafe.shop.model.product.ProductDataAccess;
import poussecafe.shop.model.product.ProductId;
import poussecafe.storage.internal.InternalDataAccess;
import poussecafe.storage.internal.InternalStorage;

@DataAccessImplementation(
    aggregateRoot = Product.Root.class,
    dataImplementation = ProductAttributes.class,
    storageName = InternalStorage.NAME
)
public class ProductInternalDataAccess
extends InternalDataAccess<ProductId, ProductAttributes>
implements ProductDataAccess<ProductAttributes> {

}
