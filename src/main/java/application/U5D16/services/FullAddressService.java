package application.U5D16.services;


import application.U5D16.entities.FullAddress;
import application.U5D16.repository.FullAddressRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FullAddressService {

    @Autowired
    FullAddressRepository fullAddressRepo;

    String line = "";


    public void readCsvAndStoreInDb(String filePath) {


        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            BufferedReader br = new BufferedReader(new FileReader("src/main/excels/comuni-italiani.csv"));
            BufferedReader br2 = new BufferedReader(new FileReader("src/main/excels/province-italiane.csv"));
            while ((line=br.readLine()) != null ){
                String[] data = line.split(";");
                if (data[3].equals("provincia"))continue;
                FullAddress fullAddress = new FullAddress();
                fullAddress.setProvincia(data[3]);

                fullAddress.setDenominazioneInItaliano(data[2]);
                while ((line=br2.readLine()) != null ){
                    String[] data2 = line.split(";");
                    if(data[3].equals(data2[1])){
                        fullAddress.setRegione(data2[2]);
                        fullAddress.setSigla(data2[0]);
                    }
                }
                System.out.println(fullAddress);
                fullAddressRepo.save(fullAddress);
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}
