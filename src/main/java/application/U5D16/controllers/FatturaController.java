package application.U5D16.controllers;

import application.U5D16.entities.Fattura;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.payloads.user.FatturaDTO;
import application.U5D16.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fattura")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;


    @GetMapping("")
    public Page<Fattura> getAllFatture(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "15") int size,
                                      @RequestParam(defaultValue = "id") String orderBy){
        return fatturaService.getFatture(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Fattura findById(@PathVariable UUID id) {
        return fatturaService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura saveNewFattura(@RequestBody @Validated FatturaDTO newFattura, BindingResult validation){

        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return fatturaService.saveFattura(newFattura);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura findByIdAndUpdate(@PathVariable UUID id, @RequestBody @Validated FatturaDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return fatturaService.findFatturaAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        fatturaService.findAddressByUUIDAndDelete(id);
    }



    @GetMapping("/client/{id}")
    public List<Fattura> getFattureByClient(@PathVariable UUID id){
        return fatturaService.findFatturaFromClientId(id);
    }



}
