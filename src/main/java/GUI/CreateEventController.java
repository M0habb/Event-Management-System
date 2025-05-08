package GUI;

import classes.Database;
import classes.Event;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;


public class CreateEventController {

    @FXML
    private TextField typeTextfield;

    @FXML
    private TextField nameTextfield;

    @FXML
    private ComboBox<String> rentComboBox;

    @FXML
    private TextField priceTextField;

    @FXML
    private DatePicker eventDate;

    @FXML
    private CheckBox outdoorsCheckbox;

    @FXML
    private void initialize(){
        for (int i = 0; i < Database.rooms.size(); i++){
            if (Database.rooms.get(i).getAvailable()){
                if (outdoorsCheckbox.isSelected() && Database.rooms.get(i).isOutdoors()){
                    rentComboBox.getItems().add(Database.rooms.get(i).getRoomName());
                }else if (!outdoorsCheckbox.isSelected() && !Database.rooms.get(i).isOutdoors()){
                    rentComboBox.getItems().add(Database.rooms.get(i).getRoomName());
                }
            }
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/organizerLanding.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void handleCreate (ActionEvent actionEvent) throws IOException {

        String name = nameTextfield.getText();
        Date birthdate = Date.from(Instant.from(eventDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        

        // return to landing
        Parent root = FXMLLoader.load(getClass().getResource("/resources/organizerLanding.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }


    @FXML
    private void handleOutdoor(){

        rentComboBox.getItems().clear();
        
        for (int i = 0; i < Database.rooms.size(); i++){
            if (Database.rooms.get(i).getAvailable()){
                if (outdoorsCheckbox.isSelected() && Database.rooms.get(i).isOutdoors()){
                    rentComboBox.getItems().add(Database.rooms.get(i).getRoomName());
                }else if (!outdoorsCheckbox.isSelected() && !Database.rooms.get(i).isOutdoors()){
                    rentComboBox.getItems().add(Database.rooms.get(i).getRoomName());
                }
            }
        }

    }
    
}

