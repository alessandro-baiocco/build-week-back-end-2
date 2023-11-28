package application.U5D16.controllers;


import application.U5D16.entities.Client;
import application.U5D16.payloads.client.NewClientDTO;
import application.U5D16.payloads.client.PutClientDTO;
import application.U5D16.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("")
    public Page<Client> getAllClients(@RequestParam(defaultValue = "0")int page ,
                                      @RequestParam(defaultValue = "10")int size,
                                      @RequestParam(defaultValue = "id")String order,
                                      @RequestParam(defaultValue = "true")boolean ascending ){
        return clientService.getALlClients(page , size , order , ascending );
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable UUID id){
        return clientService.findById(id);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client putProfile(@PathVariable UUID id, @RequestBody PutClientDTO body){
        return clientService.findClientByIdAndUpdate(id , body);
    }

    @PatchMapping("/upload/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadPicture(@PathVariable UUID id, @RequestParam("avatar")MultipartFile file) throws IOException {
        return clientService.imageUpload(id, file);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client saveClient(@RequestBody NewClientDTO body ) throws IOException {
        return clientService.saveClient(body);
    }




    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        clientService.findClientByUUIDAndDelete(id);
    }


    @GetMapping("/minfatturato")
    public List<Client> getClientiByFatturatoMinimo(@RequestParam double minFatturato) {
        return clientService.getClientiByFatturatoMinimo(minFatturato);
    }

    @GetMapping("/maxfatturato")
    public List<Client> getClientiByFatturatoMassimo(@RequestParam double maxFatturato) {
        return clientService.getClientiByFatturatoMax(maxFatturato);
    }

    @GetMapping("/fatturato")
    public List<Client> getClientiByFatturatoRange(@RequestParam  double minFatturato, @RequestParam double maxFatturato) {
        return clientService.findByRangeFatturatoAnnuale(minFatturato , maxFatturato);
    }

    @GetMapping("/minInserimento")
    public List<Client> getClientiByDataDiInserimentoMinimo(@RequestParam LocalDate to) {
        return clientService.getClientiByDataDiInserimentoMinimo(to);
    }

    @GetMapping("/maxInserimento")
    public List<Client> getClientiByDataDiInserimentoMassima(@RequestParam LocalDate from) {
        return clientService.getClientiByDataDiInserimentoMassima(from);
    }

    @GetMapping("/dataDiInserimento")
    public List<Client> findByRangeDataDiInserimento(@RequestParam  LocalDate to, @RequestParam LocalDate from) {
        return clientService.findByRangeDataDiInserimento(to , from);
    }


    @GetMapping("/minContatto")
    public List<Client> getClientiByDataUltimoContattoMinimo(@RequestParam LocalDate to) {
        return clientService.getClientiByDataUltimoContattoMinimo(to);
    }

    @GetMapping("/maxContatto")
    public List<Client> getClientiByDataUltimoContattoMassimo(@RequestParam LocalDate from) {
        return clientService.getClientiByDataUltimoContattoMassima(from);
    }

    @GetMapping("/ultimoContatto")
    public List<Client> findByRangeDataUltimoContatto(@RequestParam  LocalDate to, @RequestParam LocalDate from) {
        return clientService.findByRangeDataUltimoContatto(to , from);
    }

    @GetMapping("/nomeContatto")
    public List<Client> findByNameStartingWith(@RequestParam String name){
       return clientService.findByNomeContattoStartingWith(name);

    }








}
