package qindelgroup.JJOOwebService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class MasterDAO {

	private Connection dbConnection;

	public MasterDAO() {
		dbConnection = null;
	}

	public ArrayList<HashMap<String, String>> getCiudadesCompleto() {
		dbConnection = ConnectManager.getConnection();
		ArrayList<HashMap<String, String>> toret = new ArrayList<>();

		Statement consulta;
		try {
			consulta = dbConnection.createStatement();
			consulta.executeQuery("select P.ID_PAIS, P.NOMBRE_PAIS as Pais, "
					+ "	C.ID_CIUDAD, C.NOMBRE_CIUDAD as Ciudad, " + "	ifnull(C.VALOR_CIUDAD, P.VALOR_PAIS) as Valor,"
					+ "	sub.DESCRIPCION_TIPO as Descripcion,"
					+ "	(select count(sede_jjoo.AÑO) from sede_jjoo where sede_jjoo.SEDE=C.ID_CIUDAD) as Numero_veces_sede "
					+ "from pais as P, ciudad as C "
					+ "left join (select DESCRIPCION_TIPO, SEDE from tipo_jjoo as TJ, sede_jjoo as SJ where TJ.ID_TIPO_JJOO=SJ.ID_TIPO_JJOO) as sub "
					+ "on sub.SEDE = C.ID_CIUDAD " + "where C.ID_PAIS = P.ID_PAIS" + "");
			ResultSet resultados = consulta.getResultSet();
			HashMap<String, String> newElement;
			while (resultados.next()) {
				newElement = new HashMap<>();
				newElement.put("ID_PAIS", Integer.toString(resultados.getInt("ID_PAIS")));
				newElement.put("NOMBRE_PAIS", resultados.getString("Pais"));
				newElement.put("ID_CIUDAD", Integer.toString(resultados.getInt("ID_CIUDAD")));
				newElement.put("NOMBRE_CIUDAD", resultados.getString("Ciudad"));
				newElement.put("VALOR", Integer.toString(resultados.getInt("VALOR")));
				newElement.put("DESCRIPCION_TIPO", resultados.getString("Descripcion"));
				newElement.put("NUMERO_VECES_SEDE", Integer.toString(resultados.getInt("Numero_veces_sede")));
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
}
