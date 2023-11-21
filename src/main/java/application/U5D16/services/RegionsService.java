package application.U5D16.services;


import application.U5D16.entities.Region;
import application.U5D16.repositories.RegionsRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class RegionsService {

    @Autowired
    private RegionsRepository regionsRepo;

    String line = "";
    String line2 = "";


    public void readCsvAndStoreInDb(String filePath) throws IOException {
        List<Region> listOfFullAddress = new ArrayList<>();
        List<String> regioniCitta = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            BufferedReader br = new BufferedReader(new FileReader("src/main/excels/comuni-italiani.csv"));
            BufferedReader br2 = new BufferedReader(new FileReader("src/main/excels/province-italiane.csv"));

            while ((line2=br2.readLine()) != null ){
               regioniCitta.add(line2);

                }




            while ((line=br.readLine()) != null ){
                String[] data = line.split(";");
                if (data[3].equals("provincia"))continue;
                Region fullAddress = new Region();
                fullAddress.setProvincia(data[3]);
                fullAddress.setDenominazioneInItaliano(data[2]);
                switch (data[3].replace("-" , " ")){
                    case "Sud Sardegna":
                        fullAddress.setRegione("Sardegna");
                        fullAddress.setSigla("SU");
                        break;
                    case "Valle d'Aosta/Vallée d'Aoste":
                            fullAddress.setRegione("Valle d'Aosta");
                            fullAddress.setSigla("VdA");
                            break;
                    case "Verbano Cusio Ossola":
                        fullAddress.setRegione("Piemonte");
                        fullAddress.setSigla("VCO");
                        break;
                    default:
                        for(int j = 0 ; j < regioniCitta.size() ; j++){
                            String[] data2 = regioniCitta.get(j).split(";");
                            if(data[3].trim().equalsIgnoreCase(data2[1].trim())){
                                fullAddress.setRegione(data2[2]);
                                fullAddress.setSigla(data2[0]);
                            }
                        }
                }



                    regionsRepo.save(fullAddress);
                }






        } catch ( IOException e) {
            e.printStackTrace();
        }
    }





}
