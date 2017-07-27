package qindelgroup.JJOOwebService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SedeDAO {
	private Connection dbConnection;
	
	public SedeDAO() {
		dbConnection = null;
	}
	
	public boolean insertNuevaSede(int anyo, int idTipoJJOO, int idCiudad) {
		dbConnection = ConnectManager.getConnection();
		Statement consulta;
		try {
			consulta = dbConnection.createStatement();
			consulta.executeUpdate("INSERT INTO SEDE_JJOO VALUES ("+anyo+", "+idTipoJJOO+", "+idCiudad+")");
			consulta.close();
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteSede(int anyo, int id_tipo) {
		dbConnection = ConnectManager.getConnection();
		Statement consulta;
		try {
			consulta = dbConnection.createStatement();
			consulta.executeUpdate("DELETE FROM SEDE_JJOO WHERE AÑO = "+anyo+ " and ID_TIPO_JJOO = "+id_tipo);
			consulta.close();
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList<HashMap<String,String>> getSedes(){
		dbConnection = ConnectManager.getConnection();
		ArrayList<HashMap<String, String>> toret = new ArrayList<>();

		Statement consulta;
		try {
			consulta = dbConnection.createStatement();
			consulta.executeQuery(	"select NOMBRE_CIUDAD, AÑO as ANYO, DESCRIPCION_TIPO, sede_jjoo.ID_TIPO_JJOO "
									+ "from ciudad, sede_jjoo, tipo_jjoo "
									+ "where ciudad.ID_CIUDAD = sede_jjoo.SEDE and sede_jjoo.ID_TIPO_JJOO = tipo_jjoo.ID_TIPO_JJOO");
			ResultSet resultados = consulta.getResultSet();
			HashMap<String, String> newElement;
			while (resultados.next()) {
				newElement = new HashMap<>();
				newElement.put("NOMBRE_CIUDAD", resultados.getString("NOMBRE_CIUDAD"));
				newElement.put("ANYO", Integer.toString(resultados.getInt("ANYO")));
				newElement.put("DESCRIPCION_TIPO", resultados.getString("DESCRIPCION_TIPO"));
				newElement.put("ID_TIPO_JJOO", Integer.toString(resultados.getInt("ID_TIPO_JJOO")));
				toret.add(newElement);
			}
			resultados.close();
			consulta.close();
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return toret;
	
	}

	public HashMap<String, Integer> getByAnyoTipo(int id, int tipo) {
		dbConnection = ConnectManager.getConnection();
		HashMap<String, Integer> toret = new HashMap<>();

		Statement consulta;
		try {
			consulta = dbConnection.createStatement();
			consulta.executeQuery(	"select * from sede_jjoo where AÑO ="+id+" and ID_TIPO_JJOO = "+ tipo);
			ResultSet resultados = consulta.getResultSet();
			resultados.next();
			toret.put("ID_TIPO_JJOO", resultados.getInt("ID_TIPO_JJOO"));
			toret.put("SEDE", resultados.getInt("SEDE"));
			resultados.close();
			consulta.close();
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return toret;
	}

	public boolean updateSede(int id, int id_tipo, int id_sede) {
		dbConnection = ConnectManager.getConnection();
		Statement consulta;
		try {
			consulta = dbConnection.createStatement();
			consulta.executeUpdate("update sede_jjoo set ID_TIPO_JJOO = "+ id_tipo + ", SEDE = " + id_sede + " where AÑO = "+ id + " and ID_TIPO_JJOO = "+ id_tipo);
			consulta.close();
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
