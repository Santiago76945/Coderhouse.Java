package santiagohaspert.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import santiagohaspert.jpa.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
