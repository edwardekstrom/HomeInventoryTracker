package persistance;

import java.sql.*;


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

	}

	public static void end(){
		
	}
}