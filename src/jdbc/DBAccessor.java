package jdbc;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Tracy
 */
public interface DBAccessor {
    public abstract void openConnection(String dbUrl, String username, String password)
            throws IllegalArgumentException, ClassNotFoundException, SQLException;
    
    public abstract void closeConnection() throws SQLException;
    
    public abstract List findRecords(String sqlStatement, boolean closeConnection) 
            throws SQLException, Exception;
}
