package application.U5D16.repositories;

import application.U5D16.entities.Client;
import application.U5D16.entities.Fattura;
import application.U5D16.entities.enums.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

    List<Fattura> findByDataGreaterThanEqual(LocalDate to);


    List<Fattura> findByDataLessThanEqual(LocalDate from);


    List<Fattura> findByDataBetween(LocalDate to , LocalDate from);


    List<Fattura> findByImportoGreaterThanEqual(double minImporto);


    List<Fattura> findByImportoLessThanEqual(double maxImporto);


    List<Fattura> findByImportoBetween(double minImporto , double maxImporto);

    List<Fattura> findByStatoLike(StatoFattura stato);



}

