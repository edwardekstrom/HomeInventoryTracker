package persistance;

import java.sql.*;
import java.io.File;


public class SQLTransactionManager{

	private static Connection _connection;

	public static void initialize() {
		
		try {
			final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
			Class.forName(driver);
		}
		catch(ClassNotFoundException e) {
		}
		
	}

	public static Connection getConnection(){
		return _connection;
	}

	public static void begin(){

		String dbName = "db" + File.separator + "bookclub";
		String connectionURL = "jdbc:derby:" + dbName + ";create=false";
		
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