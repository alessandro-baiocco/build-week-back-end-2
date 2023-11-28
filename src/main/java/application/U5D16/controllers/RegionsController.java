package application.U5D16.controllers;



import application.U5D16.entities.Region;
import application.U5D16.repositories.RegionsRepository;
import application.U5D16.services.RegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionsController {
    @Autowired
    private RegionsService regionsService;

    @GetMapping("")
    private List<Region> getAllRegion(){
     return  regionsService.getAllRegions();
    }


    @GetMapping("/provincie")
    private List<String> getProvincie(){
        return regionsService.getProvincie();
    }

    @GetMapping("/provincia/regione")
    private List<Region> getRegione(@RequestParam String provincia){
        return regionsService.getRegione(provincia);
    }







}
