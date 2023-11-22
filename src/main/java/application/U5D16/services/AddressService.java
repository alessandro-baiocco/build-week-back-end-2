package application.U5D16.services;

import application.U5D16.entities.Address;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.exceptions.NotFoundException;
import application.U5D16.payloads.user.AddressDTO;
import application.U5D16.payloads.user.UpdateAddressDTO;
import application.U5D16.repositories.AddressRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


   
    @Autowired
    private Cloudinary cloudinary;

    public Page<Address> getAllAddresses(int page, int size, String orderBy)
 
    {
        Pageable addressPageable = PageRequest.of(page, size, Sort.by(orderBy));
        return addressRepository.findAll(addressPageable);
    }

    public Address findById(UUID uuid) throws NotFoundException {

        return addressRepository.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public Address saveAddress(Address newAddress){

        addressRepository.findByVia(newAddress.getVia()).ifPresent(position -> {throw new
                BadRequestException("The address added already exists");

        });

        Address newLocationAddress = new Address();

        newLocationAddress.setVia(newAddress.getVia());
        newLocationAddress.setCap(newAddress.getCap());
        newLocationAddress.setComune(newAddress.getComune());
        return addressRepository.save(newLocationAddress);
    }



    public Address saveAddressByClient(Address newAddress){

        Address newLocationAddress = new Address();

        newLocationAddress.setVia(newAddress.getVia());
        newLocationAddress.setCap(newAddress.getCap());
        newLocationAddress.setComune(newAddress.getComune());
        return addressRepository.save(newLocationAddress);
    }

    public Address findAddressByIdAndUpdate(UUID uuid, UpdateAddressDTO body){

        Address foundAddress = this.findById(uuid);

        if (body.via() != null){
            foundAddress.setVia(body.via());
        }

        if (body.comune() != null){
            foundAddress.setComune(body.comune());
        }

        return addressRepository.save(foundAddress);
    }

    public void findAddressByUUIDAndDelete(UUID uuid) throws NotFoundException {
        Address foundAddress = this.findById(uuid);
        addressRepository.delete(foundAddress);
    }







}
