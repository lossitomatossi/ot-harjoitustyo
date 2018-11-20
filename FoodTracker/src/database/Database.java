package database;

import java.sql.*;


public class Database {
    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    public void init() {
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();
            
            
        } catch (Throwable t) {
            System.out.println("Error >> " + t.getMessage());
        }
    }
}
