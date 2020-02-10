package poussecafe.shop.domain.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.domain.Product;
import poussecafe.shop.domain.ProductId;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(
        aggregateRoot = Product.class,
        dataImplementation = ProductData.class,
        storageName = SpringJpaStorage.NAME
)
public class ProductJpaDataAccess extends JpaDataAccess<ProductId, ProductData, String> implements poussecafe.shop.domain.ProductDataAccess<ProductData> {

    @Autowired
    private ProductJpaRepository repository;

    @Override
    protected String convertId(ProductId id) {
        return id.stringValue();
    }

    @Override
    protected JpaRepository<ProductData, String> jpaRepository() {
        return repository;
    }
}
