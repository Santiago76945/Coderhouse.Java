package santiagohaspert.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import santiagohaspert.jpa.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
