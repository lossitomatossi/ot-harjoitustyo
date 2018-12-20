package foodtracker.utilities;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

/**
 * A class to reduce the amount of code needed in the gui class, creates
 * Alert objects based on the user's input
 * @author terala
 */
public class AlertMessage {
    private Alert alert;
    
    /**
     * Creates an Alert and assigns it a title with setTitle(title), a header 
     * with setHeader(header) and content by using a Text element to enable text
     * wrapping and then placing the text element as the content for the Alert.
     * @param title the title for an alert in String format
     * @param header the header for an alert in String format
     * @param contentText the contentText for an alert in String format
     */
    public AlertMessage(String title, String header, String contentText) {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        Text text = new Text(contentText);
        text.setWrappingWidth(400);
        alert.getDialogPane().setContent(text);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
    
    

    
}
