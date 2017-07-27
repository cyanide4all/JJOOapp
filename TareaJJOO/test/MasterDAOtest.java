import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
public class MasterDAOtest {

	@Test
	public void testGetCiudadesCompleto() {
		MasterDAO daoTest = new MasterDAO();
		SedeDAO sedeDAO = new SedeDAO();

		ArrayList<HashMap<String,String>> resultados1 = daoTest.getCiudadesCompleto();

		int countSedesIndex1before = 0;
		System.out.println(resultados1);
		for(HashMap<String, String> i : resultados1) {
			if(Integer.parseInt(i.get("ID_CIUDAD")) == 1) {
				countSedesIndex1before = Integer.parseInt(i.get("NUMERO_VECES_SEDE"));
			}
		}
		sedeDAO.insertNuevaSede(5555, 1, 1); 

		ArrayList<HashMap<String,String>> resultados2 = daoTest.getCiudadesCompleto();

		int countSedesIndex1After = 0;

		for(HashMap<String, String> i : resultados2) {
			if(Integer.parseInt(i.get("ID_CIUDAD")) == 1) {
				countSedesIndex1After = Integer.parseInt(i.get("NUMERO_VECES_SEDE"));
			}
		}
		
		sedeDAO.deleteSede(5555, 1);
		assertEquals(countSedesIndex1before + 1, countSedesIndex1After);
	}

}
