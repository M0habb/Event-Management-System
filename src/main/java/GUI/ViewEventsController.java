package GUI;

import classes.Attendee;
import classes.Database;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ViewEventsController {

    @FXML private ScrollPane scrollpane;

    @FXML private Button musicCategory;
    @FXML private Button sportsCategory;
    @FXML private Button theaterCategory;
    @FXML private Button conferenceCategory;
    @FXML private Button otherCategory;
    @FXML private Label usernameLabel;

    User currentUserU = Attendee.currentUser;
    Attendee currentUser = (Attendee) currentUserU;

    private List<Button> categoryButtons;
    private Map<Button, String> categoryButtonColors;


    @FXML
    public void initialize() {

        for (int i =0; i < Database.events.size(); i++){
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
            Label eventFees = (Label) innerV.getChildren().get(4);

            VBox rootV = (VBox) scrollpane.getContent();
            rootV.getChildren().add(newVBox);
        }

        usernameLabel.setText(currentUser.getUserName());

        categoryButtons = Arrays.asList(musicCategory, sportsCategory, theaterCategory, conferenceCategory, otherCategory);

        // Assign each button its unique color
        categoryButtonColors = new HashMap<>();
        categoryButtonColors.put(musicCategory, "#f1c40f");
        categoryButtonColors.put(sportsCategory, "#16a085");
        categoryButtonColors.put(theaterCategory, "#3498db");
        categoryButtonColors.put(conferenceCategory, "#9b59b6");
        categoryButtonColors.put(otherCategory, "#95a5a6");
    }

    private void toggleBackground(Button clickedButton) {
        for (Button b : categoryButtons) {
            b.setStyle("-fx-background-color: transparent;");
        }

        // Apply the unique color if it's not already set
        String color = categoryButtonColors.get(clickedButton);
        if (!clickedButton.getStyle().contains(color)) {
            clickedButton.setStyle("-fx-background-color: " + color + ";");
        }
    }

    @FXML
    private void handleMusicCategory(){
        toggleBackground(musicCategory);
    }

    @FXML
    private void handleSportsCategory(){
        toggleBackground(sportsCategory);
    }

    @FXML
    private void handleTheaterCategory(){
        toggleBackground(theaterCategory);
    }

    @FXML
    private void handleConferenceCategory(){
        toggleBackground(conferenceCategory);
    }

    @FXML
    private void handleOtherCategory(){
        toggleBackground(otherCategory);
    }

    @FXML
    private void handleSignout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
