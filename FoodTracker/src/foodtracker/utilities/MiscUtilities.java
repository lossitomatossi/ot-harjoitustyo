package foodtracker.utilities;

/**
 * Class containing methods used by the UI
 * @author Tomas
 */
public class MiscUtilities {

    /**
     * Class to reduce text in the UI
     */
    public MiscUtilities() {
    }
    
    /**
     * This method tells the quantityType of the given food based on the
     * first letter of the word input
     * @param string the String that the user inputs
     * @return foodtype that starts with the same letters as the user's input
     */
    public String whichQuantityType(String string) {
        if (string.startsWith("p")) {
            return "pieces";
        } else if (string.startsWith("g")){
            return "grams";
        } else if (string.startsWith("l")) {
            return "liters";
        } else {
            return string;
        }
    }
    
    /**
     * This method tells the foodType of the given food based on the first letter
     * of a textField input
     * @param string the given text
     * @return the foodType that starts with the same letter
     */
    public String whichType(String string) {
        if (string.startsWith("f")) {
            return "fresh";
        } else if (string.startsWith("p")){
            return "prepared";
        } else if (string.startsWith("i")) {
            return "ingredient";
        } else {
            return "unknown";
        }
    }
}
