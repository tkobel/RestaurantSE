package model;

import java.util.List;

/**
 *
 * @author Tracy
 */
public interface DAOStrategy {
    public abstract List<MenuItem> getMenuItems() throws RuntimeException;
}
