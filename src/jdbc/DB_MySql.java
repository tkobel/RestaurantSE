package jdbc; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * An implementation of DBAccessor for use with MySQL.
 * @author Tracy
 */
public class DB_MySql implements DBAccessor {
    private Connection connection;
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String URL_ERROR = "Invalid URL. May not be null or zero length.";
    
    @Override
    public void openConnection(String dbUrl, String username, String password) 
        throws IllegalArgumentException, ClassNotFoundException, SQLException {       
	if( dbUrl == null || dbUrl.length() == 0 ) throw new IllegalArgumentException(URL_ERROR);
	username = (username == null) ? "" : username;
	password = (password == null) ? "" : password;
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbUrl, username, password);           
        }
    
    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
