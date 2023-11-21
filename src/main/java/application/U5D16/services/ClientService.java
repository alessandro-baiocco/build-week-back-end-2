package application.U5D16.services;

import application.U5D16.entities.Client;
import application.U5D16.entities.User;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.exceptions.NotFoundException;
import application.U5D16.payloads.client.ClientDTO;
import application.U5D16.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Page<Client> getClients(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return clientRepository.findAll(pageable);
    }
    public Client save(ClientDTO body) throws BadRequestException {
        clientRepository.findByEmail(body.email()).ifPresent(client -> {
            throw new BadRequestException("L'email " + client.getEmail() + " è già utilizzata");
        });
        Client client= new Client();
        client.setRagioneSociale(body.ragioneSociale());
        client.setPartitaIva(body.partitaIva());
        client.setEmail(body.email());
        client.setDataInserimento(body.dataInserimento());
        client.setDataUltimoContatto(body.dataUltimoContatto());
        client.setFatturatoAnnuale(body.fatturatoAnnuale());
        client.setPec(body.pec());
        client.setFormaGiuridica(body.formaGiuridica());
        client.setTelefono(body.telefono());
        client.setNomeContatto(body.nomeContatto());
        client.setCognomeContatto(body.cognomeContatto());
        client.setEmailContatto(body.emailContatto());
        client.setTelefonoContatto(body.telefonoContatto());
        return clientRepository.save(client);
    }

    public Client findById(UUID id) throws NotFoundException{
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void  findByIdAndDelete(UUID id) throws NotFoundException{
        Client found = this.findById(id);
        clientRepository.delete(found);
    }

    public Client findByIdAndUpdate(UUID id, ClientDTO body) throws  NotFoundException{
        Client found = this.findById(id);
        found.setRagioneSociale(body.ragioneSociale());
        found.setPartitaIva(body.partitaIva());
        found.setEmail(body.email());
        found.setDataInserimento(body.dataInserimento());
        found.setDataUltimoContatto(body.dataUltimoContatto());
        found.setFatturatoAnnuale(body.fatturatoAnnuale());
        found.setPec(body.pec());
        found.setFormaGiuridica(body.formaGiuridica());
        found.setTelefono(body.telefono());
        found.setNomeContatto(body.nomeContatto());
        found.setCognomeContatto(body.cognomeContatto());
        found.setEmailContatto(body.emailContatto());
        found.setTelefonoContatto(body.telefonoContatto());
        return clientRepository.save(found);
    }

    public Client findByEmail(String email){
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("cliente con email " + email + " non trovato"));
    }

}
