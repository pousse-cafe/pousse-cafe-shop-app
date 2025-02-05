package poussecafe.shop.core.model.product.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.core.model.product.Product;
import poussecafe.shop.core.model.product.ProductDataAccess;
import poussecafe.shop.core.model.product.ProductId;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
        aggregateRoot = Product.Root.class,
        dataImplementation = ProductAttributes.class,
        storageName = SpringJpaStorage.NAME
)
public class ProductSpringJpaDataAccess extends JpaDataAccess<ProductId, ProductAttributes, String> implements ProductDataAccess<ProductAttributes> {

    @Autowired
    private ProductAttributesJpaRepository repository;

    @Override
    protected String convertId(ProductId id) {
        return id.stringValue();
    }

    @Override
    protected JpaRepository<ProductAttributes, String> jpaRepository() {
        return repository;
    }
}
