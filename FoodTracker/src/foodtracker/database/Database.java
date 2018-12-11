package foodtracker.database;

import java.sql.*;

/**
 * The class required to connect to the database file the program is using
 * @author terala
 */
public class Database {

    private String databaseAddress;

    /**
     * Creates a database object that knows where it is supposed to connect
     * @param databaseAddress a String containing the name of the database
     * @throws ClassNotFoundException if the database can't be found the program
     * throws an error
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
     * Attempts to connect to the database
     * @return uses Java's built in DriverManager to get a connection which is
     * then passed on to make SQL queries possible.
     * @throws SQLException throws an error if the connection fails
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
        }
        return DriverManager.getConnection(databaseAddress);
    }
    
}