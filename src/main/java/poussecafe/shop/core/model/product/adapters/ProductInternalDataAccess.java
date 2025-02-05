package poussecafe.shop.core.model.product.adapters;

import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.core.model.product.Product;
import poussecafe.shop.core.model.product.ProductDataAccess;
import poussecafe.shop.core.model.product.ProductId;
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
