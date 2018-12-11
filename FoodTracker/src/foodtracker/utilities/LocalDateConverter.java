package foodtracker.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateConverter is used to transform LocalDates in java to a String
 * format when inserting information to the database and then back when data is
 * being pulled from the database. This is because sqlite3 and java don't have
 * Date objects that could be transformed in normal ways.
 * @author terala
 */
public class LocalDateConverter {

    /**
     * Creating a LocaldateConverter doesn't require parameters
     */
    public LocalDateConverter() {
    }
    
    /**
     * Transforms a String object that is a format of dd.MM.yyyy to a LocalDate
     * object.
     * @param string the String to transform
     * @return a LocalDate object
     */
    public LocalDate stringToDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(string, formatter);
        return localDate;
    }
    
    /**
     * Transforms a date to a String
     * @param date the LocalDate that needs to be transformed
     * @return a String version of the LocalDate in a dd.MM.yyyy format
     */
    public String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedString = date.format(formatter);
        return formattedString;
    }
    
    

}
