package application.U5D16.controllers;


import application.U5D16.entities.Client;
import application.U5D16.entities.User;
import application.U5D16.payloads.user.NewClientDTO;
import application.U5D16.services.ClientService;
import application.U5D16.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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




    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        clientService.findClientByUUIDAndDelete(id);
    }








}
