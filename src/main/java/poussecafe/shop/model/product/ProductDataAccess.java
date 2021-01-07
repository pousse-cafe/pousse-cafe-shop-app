package poussecafe.shop.model.product;

import poussecafe.domain.EntityDataAccess;

public interface ProductDataAccess<N extends ProductRoot.Attributes> extends EntityDataAccess<ProductId, N> {
}
