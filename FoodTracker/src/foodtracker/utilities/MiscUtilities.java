package foodtracker.utilities;

public class MiscUtilities {

    public MiscUtilities() {
    }
    
    
    
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
