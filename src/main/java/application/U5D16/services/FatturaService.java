package application.U5D16.services;

import application.U5D16.entities.Client;
import application.U5D16.entities.Fattura;
import application.U5D16.entities.enums.StatoFattura;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.exceptions.NotFoundException;
import application.U5D16.payloads.user.FatturaDTO;
import application.U5D16.payloads.user.NewFatturaDTO;
import application.U5D16.repositories.FatturaRepository;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

@Service
public class FatturaService {


    @Autowired
    FatturaRepository fatturaRepository;

    @Autowired
    ClientService clientService;

    public Page<Fattura> getFatture(int page, int size, String orderBy){

        Pageable fatturePageable = PageRequest.of(page, size, Sort.by(orderBy));
        return fatturaRepository.findAll(fatturePageable);
    }

    public Fattura findById(UUID uuid) throws NotFoundException {
        return fatturaRepository.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public Fattura saveFattura(NewFatturaDTO newFattura){

        Fattura addNewFattura = new Fattura();

        addNewFattura.setImporto(newFattura.importo());
        addNewFattura.setData(newFattura.data());
        addNewFattura.setNumero(newFattura.numero());
        addNewFattura.setClient(clientService.findById(newFattura.client()));
        addNewFattura.setStato(StatoFattura.INVIATA);

        return fatturaRepository.save(addNewFattura);
    }

    public Fattura findFatturaAndUpdate(UUID uuid, FatturaDTO body){

        Fattura foundFattura = this.findById(uuid);
try {
    foundFattura.setImporto(body.importo());
    foundFattura.setData(body.data());
    foundFattura.setClient(clientService.findById(body.client()));
    foundFattura.setStato(StatoFattura.valueOf(body.stato()));
    return fatturaRepository.save(foundFattura);
}catch (IllegalArgumentException ex){
    throw new BadRequestException("stato fattura non valida. gli stati sono PAGATA , NONPAGATA , SCADUTA , INVIATA");
}
    }

    public void findAddressByUUIDAndDelete(UUID uuid) throws NotFoundException {
        Fattura foundUUID = this.findById(uuid);
        fatturaRepository.delete(foundUUID);
    }

    public List<Fattura> findFatturaFromClientId(UUID clientId){
        Client found = clientService.findById(clientId);
        return found.getFatture();
    }


    public List<Fattura> findByDataGreaterThanEqual(LocalDate to){
        return fatturaRepository.findByDataGreaterThanEqual(to);
    }

    public List<Fattura> findByDataLessThanEqual(LocalDate from){
        return fatturaRepository.findByDataLessThanEqual(from);
    }


    public List<Fattura> findByDataBetween(LocalDate to , LocalDate from){
        return fatturaRepository.findByDataBetween(to, from);
    }

    public List<Fattura> findByImportoGreaterThan(double minImporto){
        return fatturaRepository.findByImportoGreaterThanEqual(minImporto);
    }

    public List<Fattura> findByImportoLessThanEqual(double maxImporto){
        return fatturaRepository.findByImportoLessThanEqual(maxImporto);
    }


    public List<Fattura> findByImportoBetween(double minImporto , double maxImporto){
        return fatturaRepository.findByImportoBetween(minImporto , maxImporto);
    }


    public List<Fattura> findByStatoLike(String stato){
    try {
    StatoFattura statoFattura = StatoFattura.valueOf(stato);
    return fatturaRepository.findByStatoLike(statoFattura);
    }catch (IllegalArgumentException ex){
        throw new BadRequestException("stato fattura non valida. gli stati sono PAGATA , NONPAGATA , SCADUTA , INVIATA");
    }
    }

    public List<Fattura> findByDataGreaterThanEqual(int minYear){
        try {
            LocalDate to = LocalDate.of(minYear , 1, 1);
            return fatturaRepository.findByDataGreaterThanEqual(to);
        }catch (IllegalArgumentException ex){
            throw new BadRequestException("anno non valido");
        }

    }

    public List<Fattura> findByDataLessThanEqual(int maxYear){
        try {
            LocalDate from = LocalDate.of(maxYear , 12, 31);
            return fatturaRepository.findByDataLessThanEqual(from);
        }catch (IllegalArgumentException ex){
            throw new BadRequestException("anno non valido");
        }



    }


    public List<Fattura> findByDataBetween(int minYear , int maxYear){

        try {
            LocalDate to = LocalDate.of(minYear , 1, 1);
            LocalDate from = LocalDate.of(maxYear , 12, 31);
            return fatturaRepository.findByDataBetween(to, from);
        }catch (IllegalArgumentException ex){
            throw new BadRequestException("anno non valido");
        }



    }








}
