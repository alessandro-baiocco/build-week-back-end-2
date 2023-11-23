package application.U5D16.controllers;

import application.U5D16.entities.Address;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.payloads.user.AddressDTO;
import application.U5D16.payloads.user.UpdateAddressDTO;
import application.U5D16.services.AddressService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public Page<Address> getAddresses(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "15") int size,
                                      @RequestParam(defaultValue = "id") String orderBy ,
                                      @RequestParam(defaultValue = "true") boolean ascending ){
        return addressService.getAllAddresses(page, size, orderBy , ascending);
    }

    @GetMapping("/{id}")
    public Address findById(@PathVariable UUID id) {
        return addressService.findById(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Address findByIdAndUpdate(@PathVariable UUID id, @RequestBody @Validated UpdateAddressDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return addressService.findAddressByIdAndUpdate(id, body);
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
    public Address saveAddress(@RequestBody @Validated Address body , BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
                return addressService.saveAddress(body);
        }
    }



}
