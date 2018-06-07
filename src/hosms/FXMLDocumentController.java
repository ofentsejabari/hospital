package hosms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author ofentse
 */
public class FXMLDocumentController implements Initializable {
    //-- Get reference to fxml components --
    @FXML
    private Button login;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    
    String passw = "pa";
    String usern = "un";
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
//                if(password.getText().trim().equals(passw) &&
//                   username.getText().trim().equals(usern)){
//                
                   new MainUI();
                   HOSMS.loginStage.close();
                    
//                }else{
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle("Authentication failed");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Please confirm your username "
//                            + "and password combination and try again.");
//                    alert.show();
//                }
                
            }
        });
    }    
    
}
