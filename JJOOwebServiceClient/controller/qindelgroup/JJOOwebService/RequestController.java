package qindelgroup.JJOOwebService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.client.RestTemplate;

public class RequestController {
	  
	//Get para la ventana principal
	public static ArrayList<HashMap<String, String>> getCiudadesCompleto(){
		ArrayList<HashMap<String, String>> toret = null;
		toret = JSONhelper.json2listmap(getResource("olimpiadas"));
		return toret;
	}

	//Get para la ventana de sedes
	public static ArrayList<HashMap<String, String>> getSedes() {
		ArrayList<HashMap<String, String>> toret = null;
		toret = JSONhelper.json2listmap(getResource("sedes"));
		return toret;
	}
	
	//Get de una sede concreta
	public static HashMap<String, Integer> getSedeByAnyoTipoJJOO(int anyo, int tipo_jjoo){
		HashMap<String, Integer> toret = null;
		toret = JSONhelper.json2stringIntMap(getResource("sedes/"+anyo+"/"+tipo_jjoo));
		return toret;
	}
	
	//Get para los tipos de JJOO
	public static String[] getDescripcionesJJOO() {
		String [] toret = JSONhelper.json2stringArray(getResource("tipoJJOO/descripciones"));
		return toret;
	}
	
	//Get para los nombres de ciudad
	public static String[] getCiudades() {
		String [] toret = JSONhelper.json2stringArray(getResource("ciudades/nombres"));
		return toret;
	}
	
	//Borrado de sedes
	public static void deleteSede(int anyo, int id_tipo) {
		URL url;
		try {
			url = new URL("http://localhost:8080/sedes/"+anyo+"/"+id_tipo);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Modificacion de sedes
	public static void updateSede(int anyo, int id_tipo, int id_sede) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://localhost:8080/sedes/"+anyo+"/"+id_tipo, id_sede);
	}
	
	//Creacion de sedes
	public static void createSede(int anyo, int id_tipo, int id_sede) {
        RestTemplate restTemplate = new RestTemplate();
        int[] body = new int[3];
        body[0] = anyo;
        body[1] = id_tipo;
        body[2] = id_sede;
		restTemplate.postForLocation("http://localhost:8080/sedes", body);
	}
	
	
	//Metodo auxiliar de conexion hacia la API REST que obtiene la respuesta como JSON
	private static String getResource(String resource) {
		String toret = null;
		try {
			//Modificar a la url del server aqui
			URL url = new URL("http://localhost:8080/" + resource);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			toret = convertStreamToString((InputStream)conn.getContent());
			conn.disconnect();
		
		}catch (Exception e) {
			//none
		}
		
		return toret;
	}
	
	//Metodo auxiliar que convierte la respuesta de las peticiones (Stream) a un string JSON
	private static String convertStreamToString(java.io.InputStream is) {
	    @SuppressWarnings("resource") //Scanner sin cerrar, no problemo
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

}
