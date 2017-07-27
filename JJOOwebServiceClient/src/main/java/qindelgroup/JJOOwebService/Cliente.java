package qindelgroup.JJOOwebService;

import java.util.ArrayList;
import java.util.HashMap;

public class Cliente {
	public static void main(String[] args) {	
		ArrayList<HashMap<String, String>> results = RequestController.getCiudadesCompleto();

		ArrayList<HashMap<String, String>> results2 = RequestController.getSedes();
		
		
		ProgramUI ui = new ProgramUI(results,results2);
		ui.DeployProgramUI();
	}
}
