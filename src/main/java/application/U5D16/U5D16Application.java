package application.U5D16;

import application.U5D16.services.FullAddressService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class U5D16Application {

	public static void main(String[] args) {
		SpringApplication.run(U5D16Application.class, args);


//
//		FullAddressService csvService = new FullAddressService();
//		csvService.readCsvAndStoreInDb("src/main/excels/comuni-italiani.csv");


	}

}
