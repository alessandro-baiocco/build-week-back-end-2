package application.U5D16.controller;



import application.U5D16.repositories.RegionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fulladdresses")
public class RegionsController {
    @Autowired
    private RegionsRepository fullAddressService;

}
