package qindelgroup.JJOOwebService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TipoJJOODAO {

	private Connection dbConnection;

	public TipoJJOODAO() {
		dbConnection = null;
	}
	
	public String[] getDescripciones(){
		dbConnection = ConnectManager.getConnection();

		String[] toret = null;
		Statement consulta;
		try {
			consulta = dbConnection.createStatement();
			consulta.executeQuery("select count(*) as n from TIPO_JJOO");
			ResultSet resultados2 = consulta.getResultSet();
			resultados2.next();
			int size = resultados2.getInt("n");
			toret = new String[size];
			consulta.executeQuery("select DESCRIPCION_TIPO from TIPO_JJOO order by ID_TIPO_JJOO");
			ResultSet resultados = consulta.getResultSet();
			int index = 0;
			while (resultados.next()) {
				toret[index++] = resultados.getString("DESCRIPCION_TIPO");
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