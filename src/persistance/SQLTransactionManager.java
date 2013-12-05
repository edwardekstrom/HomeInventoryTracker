package persistance;

import java.sql.*;
import java.io.File;


public class SQLTransactionManager{

	private static Connection _connection = null;
	private static String _db_name = "hit";

	public static void initialize() {
		
		try {
			final String driver = "org.sqlite.JDBC";
			Class.forName(driver).newInstance();
		}
		catch(Exception e) {}
		
	}

	public static Connection getConnection(){
		return _connection;
	}

	public static void begin(){

		String dbName = _db_name + ".sqlite";
		String connectionURL = "jdbc:sqlite:" + dbName;
		
		try {
			assert (_connection == null);
			
			_connection = DriverManager.getConnection(connectionURL);
			_connection.setAutoCommit(false);
			
		}
		catch (SQLException e) {
		}
		
	}

	public static void end(boolean commit){
		assert (_connection != null);		
		
		try {
			if (commit) {
				_connection.commit();
			}
			else {
				_connection.rollback();
			}
			
		}
		catch (SQLException e) {
		}
		finally {
			try{
				_connection.close();
			}catch(SQLException e){
				//Ignore
			}
		}
		
		_connection = null;
	}
}