package foodtracker.ui;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;


public class AlertMessage {
    private Alert alert;
    

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
