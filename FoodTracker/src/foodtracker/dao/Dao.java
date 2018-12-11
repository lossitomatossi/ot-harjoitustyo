package foodtracker.dao;

import java.sql.*;
import java.util.*;

/**
 * Defines the interface for Dao classes, soon not relevant because of class changes
 * @author terala
 * @param <T> defines object type
 * @param <K> the key for referencing a certain object
 */
public interface Dao<T, K> {

    /**
     * Return the wanted food object from the database by their id number
     * @param key id of the food in the database
     * @return
     * @throws SQLException
     */
    T findOne(K key) throws SQLException;

    /**
     * Finds all objects from the database
     * @return
     * @throws SQLException
     */
    List<T> findAll() throws SQLException;

    /**
     * Method used to ensure no duplicates are saved to the database, 
     * not necessary in our use case
     * @param object The object that should be compared to the databases values
     * @return
     * @throws SQLException
     */
    T saveOrUpdate(T object) throws SQLException;

    /**
     * Delete an item from the database by it's id number
     * @param key id number of the item in the database
     * @throws SQLException
     */
    void delete(K key) throws SQLException;
}
