package jdbc; 

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of DBAccessor for use with MySQL.
 * @author Tracy
 */
public class DB_MySql implements DBAccessor {
    private Connection connection;
    private String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private String URL_ERROR = "Invalid URL. May not be null or zero length.";
    
    @Override
    public void openConnection(String dbUrl, String username, String password) 
        throws IllegalArgumentException, ClassNotFoundException, SQLException {       
	if( dbUrl == null || dbUrl.length() == 0 ) throw new IllegalArgumentException(URL_ERROR);
	username = (username == null) ? "" : username;
	password = (password == null) ? "" : password;
        Class.forName(DRIVER_CLASS_NAME);
        connection = DriverManager.getConnection(dbUrl, username, password);           
        }
    
    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
    
    @Override
    public List findRecords(String sqlStatement, boolean closeConnection) 
            throws SQLException, Exception
    {
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;
        final List list=new ArrayList();
        Map record = null;
        
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlStatement);
            metaData = resultSet.getMetaData();
            final int fields = metaData.getColumnCount();
            
            while(resultSet.next()) {
                record = new HashMap();
                for (int i = 1; i <= fields; i++ ) {
                    try {
                        record.put (metaData.getColumnName(i), resultSet.getObject(i));
                    } catch (NullPointerException npe) {
                        System.err.print("npe find records");
                        System.exit(1);
                    }
                }
                list.add(record);
            }
        }
        catch (SQLException sqlex) {
            System.err.print("sqlex find records1");
            System.exit(1);
        } finally {
            try {
                statement.close();
                if(closeConnection) connection.close();
            } catch (SQLException sqle) {
                System.err.print("sqlex find records2");
            System.exit(1);
            }
        }
        
        return list;
    }
    public static void main(String[] args) {
        DBAccessor myDB = new DB_MySql();
        try{          
            myDB.openConnection("jdbc:mysql://localhost:3306/restaurant", "root", "admin");
        } catch (IllegalArgumentException iae) {
            System.err.print("iae");
            System.exit(1);
        } catch (ClassNotFoundException cnfe) {
            System.err.print("cnfe");
            System.exit(1);
        } catch (SQLException sqle) {
            
        }
        
        List<Map> testRecords = null;

        try {
            testRecords = myDB.findRecords("select * from restaurant.menu", true);
        } catch (SQLException sqle) {
            System.err.print("sqle find");
            System.exit(1);
        } catch (Exception e) {
            System.err.print("reg exception");
            System.exit(1);
        }
        testRecords.toString();
        for (Map record : testRecords) {
            System.out.println(record);
        }
        
        
        
        try{
            myDB.closeConnection();
        } catch (SQLException sqle) {
            System.err.print("sqle close");
            System.exit(1);
        }
        
        
    }
}
