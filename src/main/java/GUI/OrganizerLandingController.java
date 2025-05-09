package GUI;


import classes.Organizer;

import classes.Database;

import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class OrganizerLandingController {

    @FXML
    private ScrollPane scrollpane;

    Organizer currentUser = (Organizer) User.currentUser;

    @FXML
    private void initialize() throws IOException {
        handleUpcomingEvents();
    }

    @FXML
    private void handleSignout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void handleImageClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/createvent.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }


    private void handleUpcomingEvents() throws IOException {
        HBox rootH = (HBox) scrollpane.getContent();
        int count = 0;
        for (int i = 0; i < Database.events.size(); i++) {
            if (currentUser.getUserName().equals(Database.events.get(i).getOrganizer().getUserName())) {
                if (Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()).before(Database.events.get(i).getEventDate())) {
                    count++;
                    if (count == 1) {

                    }
                    System.out.println("Working");
                    Label mainLabel = FXMLLoader.load(getClass().getResource("/resources/hboxEvent.fxml"));

                    mainLabel.setText(Database.events.get(i).getEventName());

                    rootH.getChildren().add(mainLabel);
                }
            }
        }
    }
}
