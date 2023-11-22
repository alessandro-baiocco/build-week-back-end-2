package application.U5D16.repositories;

import application.U5D16.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FattureRepository extends JpaRepository<Fattura , UUID> {
}
