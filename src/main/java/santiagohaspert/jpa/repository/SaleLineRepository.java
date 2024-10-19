package santiagohaspert.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import santiagohaspert.jpa.entity.SaleLine;

public interface SaleLineRepository extends JpaRepository<SaleLine, Integer> {
}

