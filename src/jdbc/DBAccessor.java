package jdbc;
import java.sql.SQLException;
/**
 *
 * @author Tracy
 */
public interface DBAccessor {
    public abstract void openConnection(String dbUrl, String username, String password)
            throws IllegalArgumentException, ClassNotFoundException, SQLException;
    
    public abstract void closeConnection() throws SQLException;
}
