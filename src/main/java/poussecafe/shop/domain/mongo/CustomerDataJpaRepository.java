package poussecafe.shop.domain.mongo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDataJpaRepository extends JpaRepository<CustomerData, String> {

}
