package application.U5D16.repository;

import application.U5D16.entities.FullAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FullAddressRepository extends JpaRepository<FullAddress, UUID> {
}
