package application.U5D16.repositories;

import application.U5D16.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface RegionsRepository extends JpaRepository<Region, UUID> {
}