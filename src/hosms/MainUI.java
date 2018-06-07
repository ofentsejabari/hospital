
package hosms;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author ofentse
 */
public class MainUI extends Stage{

    public MainUI() {
        //-- Set stage title and icon--
        setTitle("Hopital Appointment System");
        getIcons().add(new Image(MainUI.class.getResourceAsStream("clinic.png")));
        
        BorderPane root = new BorderPane();
        //-- Content goes here --------------
        
        //-- Create a tabpane view and set it to the center of root --
        TabPane tabpane = new TabPane();
        root.setCenter(tabpane);
        
        //-- create and add tabs to tabpane --
        
        Tab dashboard = new Tab("Dashboard");
        Tab patients = new Tab("Patients Profile");
        Tab appointments = new Tab("Appointments");
        
        tabpane.getTabs().addAll(dashboard, patients, appointments);
        
        //-- disable the close button on tabs --
        dashboard.setClosable(false);
        patients.setClosable(false);
        appointments.setClosable(false);
        
        //-- Add contents to tabs --
        
        PatientsProfile profiles = new PatientsProfile();
        patients.setContent(profiles);
        
        //------------------------------------
        Scene scene = new Scene(root, 800, 500);
        setScene(scene);
        show();
    }
    
}
