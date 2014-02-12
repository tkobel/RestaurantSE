package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jdbc.DBAccessor;

/**
 *
 * @author Tracy
 */
public class DAOOrder {
    private DBAccessor database;
    private String DB_URL = "jdbc:mysql://localhost:3306/restaurant";
    private String USERNAME = "root";
    private String PASSWORD = "admin";
    private String SQL_ALL_ITEMS_STATEMENT = "SELECT * FROM menu";
    
    public List<MenuItem> getMenuItems() throws RuntimeException {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        
        try{
            database.openConnection(DB_URL, USERNAME, PASSWORD);
            
            List<Map> rawData = database.findRecords(SQL_ALL_ITEMS_STATEMENT, true);
            
            for (Map record : rawData) {
                int id = Integer.valueOf(record.get("menu_id").toString());
                String itemName = record.get("item_name").toString();
                double price = Double.valueOf(record.get("item_price").toString());
                MenuItem item = new MenuItem(id, itemName, price);
                menuItems.add(item);
            }
        } catch (ClassNotFoundException cnfe) {
            System.exit(1);
        } catch (SQLException sqle) {
            System.exit(1);
        } catch (Exception e) {
            System.exit(1);
        }
        
        return menuItems;
    };
}
