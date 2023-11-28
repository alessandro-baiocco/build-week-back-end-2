package application.U5D16.services;


import application.U5D16.entities.Address;
import application.U5D16.entities.Client;
import application.U5D16.entities.Fattura;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.exceptions.NotFoundException;
import application.U5D16.payloads.client.NewClientDTO;
import application.U5D16.payloads.client.PutClientDTO;
import application.U5D16.repositories.AddressRepository;
import application.U5D16.repositories.ClientRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepo;
    @Autowired
    AddressService addressService;
    @Autowired
    private Cloudinary cloudinary;



    public Page<Client> getALlClients(int page, int size, String orderBy , boolean ascending)
    {
        Pageable clientPageable = PageRequest.of(page, size, Sort.by(orderBy));

        if (!ascending) clientPageable = PageRequest.of(page, size, Sort.by(orderBy).descending());

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
        List<Fattura> fatture = new ArrayList<>();


        Address newAddress = new Address();
        newAddress.setVia(body.indirizzo().via());
        newAddress.setComune(body.indirizzo().comune());
        newAddress.setCap(body.indirizzo().cap());


        Address savedAddress = addressService.saveAddress(newAddress);

        Address clientAddress = addressService.findById(savedAddress.getId());

        newClient.setCognomeContatto(body.cognomeContatto());
        newClient.setNomeContatto(body.nomeContatto());
        newClient.setFormaGiuridica(body.formaGiuridica());
        newClient.setIndirizzo(clientAddress);
        newClient.setFatturatoAnnuale(body.fatturatoAnnuale());
        newClient.setEmailContatto(body.emailContatto());
        newClient.setTelefono(body.telefono());
        newClient.setDataUltimoContatto(LocalDate.now());
        newClient.setDataInserimento(LocalDate.now());
        newClient.setTelefonoContatto(body.telefonoContatto());
        newClient.setFatture(fatture);
        newClient.setRagioneSociale(body.ragioneSociale());
        newClient.setPec(body.pec());
        newClient.setPartitaIva(body.partitaIva());
        newClient.setEmail(body.email());
        newClient.setLogo("http://ui-avatars.com/api/?name=" + newClient.getRagioneSociale().replace(" ", ""));

        clientRepo.save(newClient);
        return newClient;
    }

    public Client findClientByIdAndUpdate(UUID uuid, PutClientDTO body){

        Client foundClient = this.findById(uuid);



    if (foundClient.getLogo().equals("http://ui-avatars.com/api/?name=" + foundClient.getRagioneSociale().replace(" ", ""))){
        foundClient.setLogo("http://ui-avatars.com/api/?name=" + body.ragioneSociale().replace(" ", ""));
    }



            foundClient.setCognomeContatto(body.cognomeContatto());

            foundClient.setNomeContatto(body.nomeContatto());


            foundClient.setFormaGiuridica(body.formaGiuridica());


            foundClient.setFatturatoAnnuale(body.fatturatoAnnuale());


            foundClient.setEmailContatto(body.emailContatto());


            foundClient.setTelefono(body.telefono());


            foundClient.setRagioneSociale(body.ragioneSociale());


            foundClient.setPec(body.pec());


            foundClient.setPartitaIva(body.partitaIva());


            foundClient.setEmail(body.email());




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
    public List<Client> getClientiByFatturatoMinimo(double minFatturato) {


        return  clientRepo.findByFatturatoAnnualeGreaterThanEqual(minFatturato);
    }

    public List<Client> getClientiByFatturatoMax(double maxFatturato) {
        return  clientRepo.findByFatturatoAnnualeLessThanEqual(maxFatturato);
    }


    public List<Client> findByRangeFatturatoAnnuale(double minFatturato , double maxFatturato){
        return  clientRepo.findByFatturatoAnnualeBetween(minFatturato , maxFatturato);


    }

    public List<Client> getClientiByDataDiInserimentoMinimo(LocalDate to) {
        return  clientRepo.findByDataInserimentoGreaterThanEqual(to);
    }

    public List<Client> getClientiByDataDiInserimentoMassima(LocalDate from) {
        return  clientRepo.findByDataInserimentoLessThanEqual(from);
    }


    public List<Client> findByRangeDataDiInserimento(LocalDate to , LocalDate from){
        return  clientRepo.findByDataInserimentoBetween(to , from);


    }


    public List<Client> getClientiByDataUltimoContattoMinimo(LocalDate to) {
        return  clientRepo.findByDataUltimoContattoGreaterThanEqual(to);
    }

    public List<Client> getClientiByDataUltimoContattoMassima(LocalDate from) {
        return  clientRepo.findByDataUltimoContattoLessThanEqual(from);
    }


    public List<Client> findByRangeDataUltimoContatto(LocalDate to , LocalDate from){
        return  clientRepo.findByDataUltimoContattoBetween(to , from);
    }


    public List<Client> findByNomeContattoStartingWith(String name){
        return clientRepo.findByNomeContattoStartingWithIgnoreCase(name);

    }






}
