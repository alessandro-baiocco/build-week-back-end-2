package application.U5D16.services;


import application.U5D16.entities.Address;
import application.U5D16.entities.Client;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.exceptions.NotFoundException;
import application.U5D16.payloads.client.NewClientDTO;
import application.U5D16.repositories.ClientRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


        Address newAddress = new Address();
        newAddress.setClient(newClient);
        newAddress.setVia(body.indirizzo().via());
        newAddress.setComune(body.indirizzo().comune());
        newAddress.setCap(body.indirizzo().cap());
        newAddress.setLocalità(body.indirizzo().località());

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
        newClient.setLogo("http://ui-avatars.com/api/?name=" + newClient.getRagioneSociale().replace(" ", ""));

        return clientRepo.save(newClient);
    }

    public Client findClientByIdAndUpdate(UUID uuid, NewClientDTO body){

        Client foundClient = this.findById(uuid);

        Address newAddress = new Address();
        newAddress.setClient(foundClient);
        newAddress.setVia(body.indirizzo().via());
        newAddress.setComune(body.indirizzo().comune());
        newAddress.setCap(body.indirizzo().cap());
        newAddress.setLocalità(body.indirizzo().località());
    if (foundClient.getLogo().equals("http://ui-avatars.com/api/?name=" + foundClient.getRagioneSociale().replace(" ", ""))){
        foundClient.setLogo("http://ui-avatars.com/api/?name=" + body.ragioneSociale().replace(" ", ""));
    }
        if (body.indirizzo() != null){
            foundClient.setIndirizzo(newAddress);
        }

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

    public String imageUpload(UUID id, MultipartFile file) throws NotFoundException, IOException{
        Client found = this.findById(id);
        String img = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setLogo(img);
        clientRepo.save(found);
        return found.getLogo();
    }
}
