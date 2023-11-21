package application.U5D16.controllers;


import application.U5D16.entities.Client;
import application.U5D16.payloads.client.NewClientDTO;
import application.U5D16.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("")
    public Page<Client> getAllClients(@RequestParam(defaultValue = "0")int page ,
                                      @RequestParam(defaultValue = "10")int size,
                                      @RequestParam(defaultValue = "id")String order){
        return clientService.getALlClients(page , size , order);
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable UUID id){
        return clientService.findById(id);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client getProfile(@PathVariable UUID id, @RequestBody NewClientDTO body){
        return clientService.findClientByIdAndUpdate(id , body);
    }

    @PatchMapping("/upload/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadPicture(@PathVariable UUID id, @RequestParam("avatar")MultipartFile file) throws IOException {
        return clientService.imageUpload(id, file);
    }

    @PatchMapping("")
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








}
