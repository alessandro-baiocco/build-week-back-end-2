package application.U5D16.repositories;

import application.U5D16.entities.Client;
import application.U5D16.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmail(String email);



    List<Client> findByFatturatoAnnualeGreaterThanEqual(double minFatturato);


    List<Client> findByFatturatoAnnualeLessThanEqual(double maxFatturato);


    List<Client> findByFatturatoAnnualeBetween(double minFatturato , double maxFatturato);



    List<Client> findByDataInserimentoGreaterThanEqual(LocalDate to);


    List<Client> findByDataInserimentoLessThanEqual(LocalDate from);


    List<Client> findByDataInserimentoBetween(LocalDate to , LocalDate from);



    List<Client> findByDataUltimoContattoGreaterThanEqual(LocalDate to);


    List<Client> findByDataUltimoContattoLessThanEqual(LocalDate from);


    List<Client> findByDataUltimoContattoBetween(LocalDate to , LocalDate from);




}

