package application.U5D16.services;


import application.U5D16.entities.Address;
import application.U5D16.entities.Client;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.exceptions.NotFoundException;
import application.U5D16.payloads.user.AddressDTO;
import application.U5D16.payloads.user.NewClientDTO;
import application.U5D16.payloads.user.UpdateAddressDTO;
import application.U5D16.repositories.AddressRepository;
import application.U5D16.repositories.ClientRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepo;
    @Autowired
    AddressService addressService;
    @Autowired
    private Cloudinary cloudinary;

    public Page<Client> getALlClients(int page, int size, String orderBy)
    {
        Pageable clientPageable = PageRequest.of(page, size, Sort.by(orderBy));
        return clientRepo.findAll(clientPageable);
    }

    public Client findById(UUID uuid) throws NotFoundException {

        return clientRepo.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public Client saveClient(NewClientDTO body){

        clientRepo.findByEmail(body.email()).ifPresent(position -> {throw new
                BadRequestException("The email added already exists");

        });

        Client newClient = new Client();


        Address newAddress = addressService.findById(body.indirizzo());

        newClient.setCognomeContatto(body.cognomeContatto());
        newClient.setFormaGiuridica(body.formaGiuridica());
        newClient.setIndirizzo(newAddress);
        newClient.setFatturatoAnnuale(body.fatturatoAnnuale());
        newClient.setEmailContatto(body.emailContatto());
        newClient.setTelefono(body.telefono());
        newClient.setTelefonoContatto(body.telefonoContatto());
        newClient.setFatture(new ArrayList<>());
        newClient.setRagioneSociale(body.ragioneSociale());
        newClient.setPec(body.pec());
        newClient.setPartitaIva(body.partitaIva());
        newClient.setEmail(body.email());

        return clientRepo.save(newClient);
    }

    public Client findClientByIdAndUpdate(UUID uuid, NewClientDTO body){

        Client foundClient = this.findById(uuid);

        if (body.cognomeContatto() != null){
            foundClient.setCognomeContatto(body.cognomeContatto());
        }
        if (body.formaGiuridica() != null){
            foundClient.setFormaGiuridica(body.formaGiuridica());
        }
        if (body.fatturatoAnnuale() != null){
            foundClient.setFatturatoAnnuale(body.fatturatoAnnuale());
        }
        if (body.emailContatto() != null){
            foundClient.setEmailContatto(body.emailContatto());
        }

        if (body.telefono() != null){
            foundClient.setTelefono(body.telefono());
        }
        if (body.ragioneSociale() != null){
            foundClient.setRagioneSociale(body.ragioneSociale());
        }
        if (body.pec() != null){
            foundClient.setPec(body.pec());
        }
        if (body.partitaIva() != null){
            foundClient.setPartitaIva(body.partitaIva());
        }
        if (body.email() != null){
            foundClient.setEmail(body.email());
        }


        return clientRepo.save(foundClient);
    }

    public void findClientByUUIDAndDelete(UUID uuid) throws NotFoundException {
        Client foundClient = this.findById(uuid);
        clientRepo.delete(foundClient);
    }


}
