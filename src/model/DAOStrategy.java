package model;

import java.util.List;

/**
 *
 * @author Tracy
 */
public interface DAOStrategy {
    List<MenuItem> getCurrentMenuChoices() throws RuntimeException;
}
