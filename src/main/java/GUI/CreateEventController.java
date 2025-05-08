package GUI;

import classes.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;


public class CreateEventController {

    Organizer currentUser = (Organizer) User.currentUser;

    @FXML
    private Label eventCreatedLabel;

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
    private ComboBox typecombobox;


    @FXML
    private Label missingLabel;

    @FXML
    private void initialize(){

        missingLabel.setVisible(false);
        eventCreatedLabel.setVisible(false);

        for (int i = 0; i < Database.rooms.size(); i++){
            if (Database.rooms.get(i).getAvailable()){
                if (outdoorsCheckbox.isSelected() && Database.rooms.get(i).isOutdoors()){
                    rentComboBox.getItems().add(Database.rooms.get(i).getRoomName());
                }else if (!outdoorsCheckbox.isSelected() && !Database.rooms.get(i).isOutdoors()){
                    rentComboBox.getItems().add(Database.rooms.get(i).getRoomName());
                }
            }
            typecombobox.getItems().addAll(
                    "Concert",
                    "Conference",
                    "Sports event"
            );
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
        boolean outdoors = outdoorsCheckbox.isSelected();
        Room room = new Room();
        for (int i = 0; i < Database.rooms.size(); i++){
            if (rentComboBox.getValue().equals(Database.rooms.get(i).getRoomName())){
                room = Database.rooms.get(i);
            }
        }
        int maxAttendees = room.getSize();

        Event event = new Event(name, birthdate, outdoors, room, maxAttendees, currentUser, new Category(1, CategoryType.MUSIC, "Test"));

        Database.events.add(event);

        eventCreatedLabel.setVisible(true);

        // return to landing
//        Parent root = FXMLLoader.load(getClass().getResource("/resources/organizerLanding.fxml"));
//
//        Scene scene = new Scene(root, 1142, 642);
//
//        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//
//        window.setScene(scene);
//        window.show();
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

