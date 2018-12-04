package foodtracker.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LocalDateConverter {

    public LocalDateConverter() {
    }
    
    
    public LocalDate stringToDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(string, formatter);
        return localDate;
    }
    
    public String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedString = date.format(formatter);
        return formattedString;
    }
    
    

}
