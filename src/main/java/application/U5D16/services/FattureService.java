package application.U5D16.services;

import application.U5D16.entities.Fattura;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.exceptions.NotFoundException;
import application.U5D16.payloads.user.FatturaDTO;
import application.U5D16.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FattureService {


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

    public Fattura saveFattura(FatturaDTO newFattura){

        fatturaRepository.findByClient(newFattura.client()).ifPresent(fatturaEsistente -> {throw new
                BadRequestException("The fattura already exists");
        });

        Fattura addNewFattura = new Fattura();

        addNewFattura.setImporto(newFattura.importo());
        addNewFattura.setData(newFattura.data());
        addNewFattura.setNumero(newFattura.numero());
        addNewFattura.setClient(clientService.findById(newFattura.client()));

        return fatturaRepository.save(addNewFattura);
    }

    public Fattura findFatturaAndUpdate(UUID uuid, FatturaDTO body){

        Fattura foundFattura = this.findById(uuid);

            foundFattura.setImporto(body.importo());
            foundFattura.setData(body.data());
            foundFattura.setNumero(body.numero());
            foundFattura.setClient(clientService.findById(body.client()));
            return fatturaRepository.save(foundFattura);
    }

    public void findAddressByUUIDAndDelete(UUID uuid) throws NotFoundException {
        Fattura foundUUID = this.findById(uuid);
        fatturaRepository.delete(foundUUID);
    }

}
