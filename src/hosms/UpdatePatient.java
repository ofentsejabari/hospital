package hosms;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ofentse
 */
public class UpdatePatient extends Stage{

    public UpdatePatient() {
        
        BorderPane root = new BorderPane();
        
        GridPane grid = new GridPane();
        grid.getStyleClass().add("form");
        grid.setVgap(5);
        grid.setHgap(5);
        
        
        TextField fname = new TextField();
        fname.setPrefWidth(280);
        fname.setPromptText("First Name");
        
        grid.add(new Label("First Name: "), 0, 0);
        grid.add(fname, 1, 0, 2, 1);
        
        TextField lname = new TextField();
        lname.setPrefWidth(280);
        lname.setPromptText("Last Name");
        
        grid.add(new Label("Last Name: "), 0, 1);
        grid.add(lname, 1, 1);
        
        TextField email = new TextField();
        email.setPrefWidth(280);
        email.setPromptText("Email");
        
        grid.add(new Label("Email: "), 0, 2);
        grid.add(email, 1, 2);
        
        TextField cell = new TextField();
        cell.setPrefWidth(280);
        cell.setPromptText("Cell Phone");
        
        grid.add(new Label("Cell Phone: "), 0, 3);
        grid.add(cell, 1, 3);
        
        TextField occupation = new TextField();
        occupation.setPrefWidth(280);
        occupation.setPromptText("Occupation");
        
        grid.add(new Label("Occupation: "), 0, 4);
        grid.add(occupation, 1, 4);
        
        HBox gendr = new HBox(10);
        gendr.setPadding(new Insets(3, 0, 3, 0));
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        
        ToggleGroup tg = new ToggleGroup();
        male.setToggleGroup(tg);
        female.setToggleGroup(tg);
        tg.selectToggle(male);
        
        gendr.getChildren().addAll(male, female);
        gendr.setAlignment(Pos.CENTER_LEFT);
        
        grid.add(new Label("Gender: "), 0, 5);
        grid.add(gendr, 1, 5);
        
        TextArea postal = new TextArea();
        postal.setPrefRowCount(3);
        postal.setPrefWidth(280);
        postal.setPromptText("Postal Address");
        
        grid.add(new Label("Postal Address: "), 0, 6);
        grid.add(postal, 1, 6);
        
        
        TextArea physical = new TextArea();
        physical.setPrefRowCount(3);
        physical.setPrefWidth(280);
        physical.setPromptText("Phyical Address");
        
        grid.add(new Label("Phyical Address: "), 0, 7);
        grid.add(physical, 1, 7);
        
        root.setCenter(grid);
        
        
        Button save = new Button("Save Changes");
        save.getStyleClass().add("custom-button");
        save.setOnAction((ActionEvent event) -> {
            if(fname.getText().trim().equals("") &&
               lname.getText().trim().equals("")){
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Mandatory field not filled up");
                alert.show();
                
            }else{
                //-- 
                Patient pt  = new Patient("", fname.getText().trim(), 
                        lname.getText().trim(),
                        cell.getText().trim(), 
                        email.getText(), "", "", "", "");
                MysqlConnector connector  = new MysqlConnector();
                
                if(connector.insertPatient(pt) == true){
                
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succefull");
                    alert.setHeaderText(null);
                    alert.setContentText("Patient has been added to the system successfully");
                    alert.show();
                }else{

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error encountered while trying to patient");
                    alert.show();
                }
            }
        });
        
        Button cancel = new Button("Exit Window");
        cancel.getStyleClass().add("custom-button");
        cancel.setOnAction((ActionEvent event) -> {
            close();
        });
        
        HBox ctrl = new HBox(10);
        ctrl.getStyleClass().add("toolbar");
        ctrl.setAlignment(Pos.CENTER_RIGHT);
        ctrl.setPadding(new Insets(5, 0, 5, 0));
        
        ctrl.getChildren().addAll(cancel, save);
        grid.add(ctrl, 0, 8, 2, 1);
        
        
        Scene scene = new Scene(root, 550, 380);
        scene.getStylesheets().add(MainUI.class.getResource("res/style.css").toExternalForm());
        
        //-- set stage icon --
        getIcons().add(new Image(MainUI.class.getResourceAsStream("res/clinic.png")));
        setScene(scene);
        setResizable(false);
        initModality(Modality.APPLICATION_MODAL);
        show();
    }
    
}
