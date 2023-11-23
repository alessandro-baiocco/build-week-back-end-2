package application.U5D16.repositories;

import application.U5D16.controllers.RegionsController;
import application.U5D16.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface RegionsRepository extends JpaRepository<Region, UUID> {

    @Query("SELECT DISTINCT r.provincia FROM Region r")
    public List<String> getProvincie();


    @Query("SELECT DISTINCT r FROM Region r WHERE LOWER(provincia) LIKE LOWER(:provincia)")
    public List<Region> getRegione(String provincia);

}
