package poussecafe.shop.domain.mongo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductData, String> {

}
