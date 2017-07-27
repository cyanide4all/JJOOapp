import java.util.ArrayList;
import java.util.HashMap;


public class Program {

	public static void main(String[] args) {
		MasterDAO daoTest = new MasterDAO();
		ArrayList<HashMap<String, String>> results = daoTest.getCiudadesCompleto();
		SedeDAO daoSedes = new SedeDAO();
		ArrayList<HashMap<String, String>> results2 = daoSedes.getSedes();
		
		
		ProgramUI ui = new ProgramUI(results,results2);
		ui.DeployProgramUI();		
		
	}

}
