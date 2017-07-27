import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CiudadDAO {

	private Connection dbConnection;

	public CiudadDAO() {
		dbConnection = null;
	}
	
	public String[] getNombres(){
		dbConnection = ConnectManager.getConnection();

		String[] toret = null;
		Statement consulta;
		try {
			consulta = dbConnection.createStatement();
			consulta.executeQuery("select count(*) as n from CIUDAD");
			ResultSet resultados2 = consulta.getResultSet();
			resultados2.next();
			int size = resultados2.getInt("n");
			toret = new String[size];
			consulta.executeQuery("select NOMBRE_CIUDAD from CIUDAD");
			ResultSet resultados = consulta.getResultSet();
			int index = 0;
			while (resultados.next()) {
				toret[index++] = resultados.getString("NOMBRE_CIUDAD");
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
