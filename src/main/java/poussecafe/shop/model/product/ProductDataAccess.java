package poussecafe.shop.model.product;

import poussecafe.domain.EntityDataAccess;

public interface ProductDataAccess<N extends Product.Root.Attributes> extends EntityDataAccess<ProductId, N> {
}
