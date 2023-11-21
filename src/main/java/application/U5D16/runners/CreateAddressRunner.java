package application.U5D16.runners;

import application.U5D16.repositories.RegionsRepository;
import application.U5D16.services.RegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CreateAddressRunner implements CommandLineRunner {
    @Autowired
    RegionsService regionsService;
    @Override
    public void run(String... args) throws Exception {

        try {
            regionsService.readCsvAndStoreInDb("src/main/excels/comuni-italiani.csv");
        }catch (IOException ex){
            System.err.println(ex.getMessage());

        }


    }
}
