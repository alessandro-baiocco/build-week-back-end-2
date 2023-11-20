package application.U5D16.controller;


import application.U5D16.repository.FullAddressRepository;
import application.U5D16.services.FullAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/fulladdresses")
public class FullAddressController {
    @Autowired
    private FullAddressService fullAddressService;

    @Autowired
    private FullAddressRepository fullAddressRepo;



}
