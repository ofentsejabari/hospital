package hosms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 *
 * @author ofentse
 */
public class PatientsProfile extends BorderPane{

    public PatientsProfile() {
        
        //-- Create a database connection (ready to execute queries)
        MysqlConnector connector  = new MysqlConnector();
        
        setStyle("-fx-background-color:green");
        
        //-- Create toolbar --
        HBox toolbar = new HBox();
        toolbar.setStyle("-fx-background-color:blue");
        toolbar.setSpacing(5);
        toolbar.setPadding(new Insets(10));
        toolbar.setAlignment(Pos.CENTER_RIGHT);
        setTop(toolbar);
        
        TableView<Patient> tableView = new TableView();
        
        Button addPatient = new Button("Add Patient");
        addPatient.setOnAction((ActionEvent event) -> {
            
            new UpdatePatient();
//            if(connector.insertPatient() == true){
//                
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Succefull");
//                alert.setHeaderText(null);
//                alert.setContentText("Patient has been added to the system successfully");
//                alert.show();
//            }else{
//                
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText(null);
//                alert.setContentText("Error encountered while trying to patient");
//                alert.show();
//            }
        });
        
        Button refresh = new Button("Refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableView.setItems(connector.getPatients());
            }
        });
        
        Label title = new Label("Patient Profiles");
        
        Region space = new Region();
        space.setStyle("-fx-background-color:orange");
        HBox.setHgrow(space, Priority.ALWAYS);
        
        toolbar.getChildren().addAll(title, space, addPatient, refresh); 
        
        //---------------------------------------------------------------------
        
        
        
        TableColumn fname = new TableColumn("FIRST NAME");
        fname.setMinWidth(100);
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        
        TableColumn sname = new TableColumn("SURNAME");
        sname.setMinWidth(100);
        sname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        
        TableColumn email = new TableColumn("EMAIL");
        email.setMinWidth(200);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        tableView.getColumns().addAll(fname, sname, email);
        
        tableView.setItems(connector.getPatients());
        setCenter(tableView);
    }
    


}