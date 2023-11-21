package application.U5D16.controllers;


import application.U5D16.entities.Address;
import application.U5D16.entities.User;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.payloads.user.AddressDTO;
import application.U5D16.payloads.user.UpdateAddressDTO;
import application.U5D16.repositories.AddressRepository;
import application.U5D16.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepo;


    @GetMapping("")
    public Page<Address> getAllAddresss(@RequestParam(defaultValue = "0")int page ,
                                    @RequestParam(defaultValue = "10")int size,
                                    @RequestParam(defaultValue = "id")String order){
        return addressService.getALlAddresses(page , size , order);
    }

    @GetMapping("/{id}")
    public Address findById(@PathVariable UUID id){
        return addressService.findById(id);
    }




    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        addressService.findAddressByUUIDAndDelete(id);
    }


    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Address saveAddress(@RequestBody @Validated AddressDTO body , BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
                return addressService.saveAddress(body);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Address putAddress(@PathVariable UUID id, @Validated @RequestBody UpdateAddressDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
                return addressService.findAddressByIdAndUpdate(id, body);
        }
    }






}
