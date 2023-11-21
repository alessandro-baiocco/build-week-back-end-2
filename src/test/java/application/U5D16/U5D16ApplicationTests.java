package application.U5D16;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class U5D16ApplicationTests {
	@Autowired
	private MockMvc mvc;
	@Test
	void contextLoads() throws Exception{
		String newJson = '{\"ragioneSociale\":\"oiwfbagbiawgbip\", \"partitaIva\":\"32412451111\",\"email\":\"obwgifuao@aip0bwf.com\",\"fatturatoAnnuale\":\"10.33\",\"pec\":\"apiwfgb@fwag.com\",\"telefono\":\"12242151\",\"emailContatto\":\"apifbwgfp@whgag.com\",\"nomeContatto\":\"franco\",\"cognomeContatto\":\"abominio\",\"telefonoContatto\":\"123456789\",\"formaGiuridica\":\"SRL\",\"indirizzo\":"{\"via\":\"aldo moreno 3\",\"cap\":\"47659\",\"comune\":\"del cazzo\"}"}';


	}

}
