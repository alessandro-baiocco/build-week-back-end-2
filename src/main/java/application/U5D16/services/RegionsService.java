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
                String rimpiazzaGliSpazzi = data[3].replace(" " , "-");
                String provinciaGiusta = rimpiazzaGliSpazzi.split("/")[0];
                switch (provinciaGiusta.toLowerCase()){
                    case "sud-sardegna":
                        fullAddress.setRegione("Sardegna");
                        fullAddress.setSigla("SU");
                        break;
                    case "pesaro-e-urbino":
                        fullAddress.setRegione("Marche");
                        fullAddress.setSigla("PU");
                        break;
                    case "verbano-cusio-ossola":
                        fullAddress.setRegione("Piemonte");
                        fullAddress.setSigla("VCO");
                        break;
                    case "monza-e-della-brianza":
                        fullAddress.setRegione("Lombardia");
                        fullAddress.setSigla("MB");
                        break;
                    case "reggio-nell'emilia":
                        fullAddress.setRegione("Emilia Romagna");
                        fullAddress.setSigla("RE");
                        break;
                    case "forlì-cesena":
                        fullAddress.setRegione("Emilia Romagna");
                        fullAddress.setSigla("FC");
                        break;
                    case "valle-d'aosta":
                        fullAddress.setRegione("AOSTA");
                        fullAddress.setSigla("AO");
                        break;
                    default:
                        for(int j = 0 ; j < regioniCitta.size() ; j++){
                            String[] data2 = regioniCitta.get(j).split(";");
                            if(provinciaGiusta.trim().equalsIgnoreCase(data2[1].trim())){
                                if(data2[0].equals("Roma")) data2[0] = "RM";
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


    public List<Region> getAllRegions(){
        return regionsRepo.findAll();
    }



    public List<String> getProvincie(){
        return regionsRepo.getProvincie();
    }

    public List<Region> getRegione(String provincia){
        return regionsRepo.getRegione(provincia);
    }







}
