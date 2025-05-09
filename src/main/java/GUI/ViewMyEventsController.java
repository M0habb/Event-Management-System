package GUI;

import classes.Address;
import classes.Attendee;
import classes.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ViewMyEventsController {

    @FXML
    private Label usernameLabel;
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private void initialize(){
        usernameLabel.setText(Attendee.currentUser.getUserName());
        displayEvents();
    }


    private void displayEvents(){
        VBox rootV = (VBox) scrollpane.getContent();
        for (int i = 0; i < Database.events.size(); i++){
            if (Database.events.get(i).getEventDate().after(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))){
                for (int j = 0; j < Database.events.get(i).getAttendees().size(); j++){
                    if (Database.events.get(i).getAttendees().get(j) == Attendee.currentUser){
                        VBox newVBox;
                        try {
                            newVBox = FXMLLoader.load(getClass().getResource("/resources/vbox_layout.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Node hbox1 = newVBox.getChildren().get(0);
                        HBox hbox = (HBox) hbox1;
                        Label eventName = (Label) hbox.getChildren().get(0);
                        Label eventDate = (Label) hbox.getChildren().get(1);

                        eventName.setText(Database.events.get(i).getEventName());
                        Date date = Database.events.get(i).getEventDate();
                        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
                        String dateString = formatter.format(date);
                        eventDate.setText(dateString);

                        VBox innerV = (VBox) newVBox.getChildren().get(1);
                        Label eventLoc = (Label) innerV.getChildren().get(0);
                        Label eventOrganizer = (Label) innerV.getChildren().get(1);
                        Label eventOutdoors = (Label) innerV.getChildren().get(2);
                        Label eventActivities = (Label) innerV.getChildren().get(3);
                        HBox bottomHBox = (HBox) innerV.getChildren().get(4);
                        Label eventFees = (Label) bottomHBox.getChildren().get(0);

                        Address address = Database.events.get(i).getRoom().getAddress();

                        eventLoc.setText(eventLoc.getText() + " " + address.country + ", " + address.city + ", " + address.street + ".");
                        eventOrganizer.setText(eventOrganizer.getText() + " " +  Database.events.get(i).getOrganizer().getUserName() + ".");
                        String outdoors = "outdoors.";
                        if (!Database.events.get(i).getOutdoors()) outdoors = "indoors.";
                        eventOutdoors.setText(eventOutdoors.getText() +  outdoors);
                        eventFees.setText(eventFees.getText() + " " +  Database.events.get(i).getFees() + ".");

                        rootV.getChildren().add(newVBox);
                    }
                }
            }
        }
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
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/attendeeLanding.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
