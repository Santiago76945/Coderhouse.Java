package santiagohaspert.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import santiagohaspert.jpa.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
