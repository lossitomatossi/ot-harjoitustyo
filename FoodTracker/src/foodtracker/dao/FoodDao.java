package foodtracker.dao;

import foodtracker.database.Database;
import foodtracker.foodtypes.FoodIngredient;
import foodtracker.foodtypes.FreshFood;
import foodtracker.foodtypes.PreparedFood;
import foodtracker.utilities.TableFood;
import foodtracker.utilities.LocalDateConverter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class FoodDao {

    private Database database;
    private LocalDateConverter converter;
    private Connection conn;

    public FoodDao(Database database) throws SQLException {
        this.database = database;
        this.converter = new LocalDateConverter();
        this.conn = database.getConnection();
    }

//    public Food findOne(Integer key) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public FreshFood findOneFresh(Integer key) throws SQLException {
        connectIfNoConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FoodItem WHERE id = ? AND foodType = 'fresh'");
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        String dateAdded = rs.getString("dateAdded");
        FreshFood ff = new FreshFood(rs.getInt("id"), rs.getString("name"), "fresh", rs.getInt("quantity"), rs.getString("quantityType"), converter.stringToDate(dateAdded));

        rs.close();
        stmt.close();
        conn.close();
        return ff;
    }

    public PreparedFood findOnePrepared(Integer key) throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FoodItem WHERE id = ? AND foodType = 'prepared'");
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        LocalDate dateAdded = converter.stringToDate(rs.getString("dateAdded"));
        LocalDate expirationDate = converter.stringToDate(rs.getString("expirationDate"));
        PreparedFood pf = new PreparedFood(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), expirationDate, dateAdded, rs.getBoolean("opened"));
        rs.close();
        stmt.close();
        conn.close();
        return pf;
    }
    
    public FoodIngredient findOneIngredient(int id) throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FoodItem WHERE id = ? AND foodType = 'ingredient'");
        stmt.setObject(1, id);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        LocalDate dateAdded = converter.stringToDate(rs.getString("dateAdded"));
        LocalDate expirationDate = converter.stringToDate(rs.getString("expirationDate"));
        FoodIngredient pf = new FoodIngredient(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), expirationDate, dateAdded);
        rs.close();
        stmt.close();
        conn.close();
        return pf;
    }

    //Combines all toStrings of foodtypes to list
    public List<String> findAll() throws SQLException {
        List<String> allFoods = new ArrayList<>();
        List<String> freshies = allFreshInString(findAllFresh());
        List<String> prepareds = allPreparedInString();
        List<String> ingredientes = allIngredientsInString(findAllIngredients());
        //findAllIngredients();
        allFoods.addAll(freshies);
        allFoods.addAll(prepareds);
        allFoods.addAll(ingredientes);
        return allFoods;
    }

    public List<FoodIngredient> findAllIngredients() throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FoodItem WHERE foodType = 'ingredient'");

        ResultSet rs = stmt.executeQuery();
        List<FoodIngredient> ingredients = new ArrayList<>();
        while (rs.next()) {
            LocalDate expirationDate = converter.stringToDate(rs.getString("expirationDate"));
            String dateAdded = rs.getString("dateAdded");
            FoodIngredient freshToAdd = new FoodIngredient(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), converter.stringToDate(dateAdded), expirationDate);
            System.out.println("Found the following ingredient from the database: " + freshToAdd.toString());
            ingredients.add(freshToAdd);
        }
        rs.close();
        stmt.close();
        conn.close();
        return ingredients;
    }

    public List<FreshFood> findAllFresh() throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FoodItem WHERE foodType = 'fresh'");

        ResultSet rs = stmt.executeQuery();
        List<FreshFood> freshFoods = new ArrayList<>();
        while (rs.next()) {
            String dateAdded = rs.getString("dateAdded");
            FreshFood freshToAdd = new FreshFood(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), converter.stringToDate(dateAdded));
            System.out.println("Found the following fresh food from the database: " + freshToAdd.toString());
            freshFoods.add(freshToAdd);
        }
        rs.close();
        stmt.close();
        conn.close();
        return freshFoods;
    }

    public List<PreparedFood> findAllPrepared() throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FoodItem WHERE foodType = 'prepared'");

        ResultSet rs = stmt.executeQuery();
        List<PreparedFood> preparedFoods = new ArrayList<>();
        while (rs.next()) {
            LocalDate dateAdded = converter.stringToDate(rs.getString("dateAdded"));
            LocalDate expirationDate = converter.stringToDate(rs.getString("expirationDate"));
            PreparedFood pf = new PreparedFood(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), expirationDate, dateAdded, rs.getBoolean("opened"));
            preparedFoods.add(pf);
            System.out.println("Found the following prepared food from the database: " + pf.toString());
        }
        rs.close();
        stmt.close();
        conn.close();
        return preparedFoods;
    }
    
    public void addFreshToDatabase(FreshFood food) throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FoodItem (name, foodType, quantity, quantityType, dateAdded) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, food.getName());
        if (food.getFoodType() == null) {
            stmt.setString(2, "unknown");
        } else {
            stmt.setString(2, food.getFoodType());
        }
        stmt.setInt(3, food.getQuantity());
        stmt.setString(4, food.getQuantityType());
        stmt.setString(5, converter.dateToString(food.getDateAdded()));
        stmt.executeUpdate();

        conn.close();
    }

    public void addIngredientToDatabase(FoodIngredient ingredient) throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FoodItem (name, foodType, quantity, quantityType, dateAdded, expirationDate) VALUES (?, ?, ?, ?, ?, ?)");
        stmt.setString(1, ingredient.getName());
        if (ingredient.getFoodType() == null) {
            stmt.setString(2, "unknown");
        } else {
            stmt.setString(2, ingredient.getFoodType());
        }
        stmt.setInt(3, ingredient.getQuantity());
        stmt.setString(4, ingredient.getQuantityType());
        stmt.setString(5, converter.dateToString(ingredient.getDateAdded()));
        stmt.setString(6, converter.dateToString(ingredient.getExpirationDate()));
        stmt.executeUpdate();
        System.out.println("Ingredient adding works" + converter.dateToString(ingredient.getDateAdded()));

        conn.close();
    }

    public void addPreparedToDatabase(PreparedFood food) throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FoodItem (name, foodType, quantity, quantityType, expirationDate, dateAdded, opened) VALUES (?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, food.getName());
        if (food.getFoodType() == null) {
            stmt.setString(2, "unknown");
        } else {
            stmt.setString(2, food.getFoodType());
        }
        System.out.println("toimiiko");
        stmt.setInt(3, food.getQuantity());
        stmt.setString(4, food.getQuantityType());
        //expirationDate
        stmt.setString(5, converter.dateToString(food.getExpirationDate()));
        //dateAdded
        stmt.setString(6, converter.dateToString(LocalDate.now()));
        stmt.setBoolean(7, food.isOpened());
        stmt.executeUpdate();

        conn.close();
    }

    public void addFreshWithId(FreshFood food, int id) throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FoodItem (id, name, foodType, quantity, quantityType, dateAdded) VALUES (?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setString(2, food.getName());
        if (food.getFoodType() == null) {
            stmt.setString(3, "unknown");
        } else {
            stmt.setString(3, food.getFoodType());
        }
        stmt.setInt(4, food.getQuantity());
        stmt.setString(5, food.getQuantityType());
        stmt.setString(6, converter.dateToString(food.getDateAdded()));
        stmt.executeUpdate();

        conn.close();
    }
    
    void addIngredientWithId(FoodIngredient fi, int id) throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FoodItem (id, name, foodType, quantity, quantityType, dateAdded, expirationDate) VALUES (?, ?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setString(2, fi.getName());
        if (fi.getFoodType() == null) {
            stmt.setString(3, "unknown");
        } else {
            stmt.setString(3, fi.getFoodType());
        }
        stmt.setInt(4, fi.getQuantity());
        stmt.setString(5, fi.getQuantityType());
        stmt.setString(6, converter.dateToString(fi.getDateAdded()));
        stmt.setString(7, converter.dateToString(fi.getExpirationDate()));
        stmt.executeUpdate();
        conn.close();
    }
    
    void addPreparedWithId(PreparedFood pf, int id) throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FoodItem (id, name, foodType, quantity, quantityType, dateAdded, expirationDate, opened) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setString(2, pf.getName());
        if (pf.getFoodType() == null) {
            stmt.setString(3, "unknown");
        } else {
            stmt.setString(3, pf.getFoodType());
        }
        stmt.setInt(4, pf.getQuantity());
        stmt.setString(5, pf.getQuantityType());
        stmt.setString(6, converter.dateToString(pf.getDateAdded()));
        stmt.setString(7, converter.dateToString(pf.getExpirationDate()));
        stmt.setBoolean(8, pf.isOpened());
        stmt.executeUpdate();
        conn.close();
    }

    public List<String> allIngredientsInString(List<FoodIngredient> given) throws SQLException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < given.size(); i++) {
            list.add(given.get(i).toString());
        }
        return list;
    }

    public List<String> allFreshInString(List<FreshFood> given) throws SQLException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < given.size(); i++) {
            list.add(given.get(i).toString());
        }
        return list;
    }

    public List<String> allPreparedInString() throws SQLException {
        List<String> list = new ArrayList<>();
        List<PreparedFood> prepared = findAllPrepared();
        for (int i = 0; i < prepared.size(); i++) {
            list.add(prepared.get(i).toString());
        }
        return list;
    }

    public Collection<String> sortedAll() throws SQLException {
        connectIfNoConnection();
        List<String> all = findAll();
        java.util.Collections.sort(all);
        return all;
    }

    public int countAll() throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM FoodItem");
        ResultSet rs = stmt.executeQuery();
        int num = rs.getInt(1);
        conn.close();
        return num;

    }

    public void delete(Integer key) throws SQLException {
        connectIfNoConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM FoodItem WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
        conn.close();
    }

    public void connectIfNoConnection() throws SQLException {
        if (conn.isClosed()) {
            this.conn = database.getConnection();
        }
    }

    public List<PreparedFood> findAllExpiringSoon() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FoodItem WHERE foodType = 'prepared'");

        ResultSet rs = stmt.executeQuery();
        List<PreparedFood> expiringPreparedFoods = new ArrayList<>();

        return expiringPreparedFoods;
    }

    public List<TableFood> preparedToTable() throws SQLException {
        List<TableFood> changedList = new ArrayList<>();
        List<PreparedFood> list = findAllPrepared();
        for (int i = 0; i < list.size(); i++) {
            TableFood table = new TableFood(list.get(i).getId(), list.get(i).getName(), list.get(i).getFoodType(), list.get(i).getQuantity(), list.get(i).getQuantityType(), list.get(i).getDateAdded(), list.get(i).getExpirationDate());
            changedList.add(table);
        }
        return changedList;
    }

    public List<TableFood> freshToTable() throws SQLException {
        List<TableFood> changedList = new ArrayList();
        List<FreshFood> list = findAllFresh();
        for (int i = 0; i < list.size(); i++) {
            TableFood table = new TableFood(list.get(i).getId(), list.get(i).getName(), list.get(i).getFoodType(), list.get(i).getQuantity(), list.get(i).getQuantityType(), list.get(i).getDateAdded(), LocalDate.now());
            changedList.add(table);
        }
        return changedList;
    }

    public List<TableFood> ingredientToTable() throws SQLException {
        List<TableFood> changedList = new ArrayList();
        List<FoodIngredient> list = findAllIngredients();
        for (int i = 0; i < list.size(); i++) {
            TableFood table = new TableFood(list.get(i).getId(), list.get(i).getName(), list.get(i).getFoodType(), list.get(i).getQuantity(), list.get(i).getQuantityType(), list.get(i).getDateAdded(), LocalDate.now());
            changedList.add(table);
        }
        return changedList;
    }

    public List<TableFood> tableFiller() throws SQLException {
        List<TableFood> list = new ArrayList();
        list.addAll(ingredientToTable());
        list.addAll(freshToTable());
        list.addAll(preparedToTable());
        return list;
    }

    public boolean deleteWithIdName(int id, String name) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FoodItem WHERE id = ? and name = ?");
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.executeUpdate();
        return false;
    }
}
