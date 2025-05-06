package GUI;

import classes.Attendee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class ViewEventsController {

    @FXML private Button musicCategory;
    @FXML private Button sportsCategory;
    @FXML private Button theaterCategory;
    @FXML private Button conferenceCategory;
    @FXML private Button otherCategory;
    @FXML private Label usernameLabel;
    @FXML private VBox expandableBox;

    private List<Button> categoryButtons;
    private Map<Button, String> categoryButtonColors;

    @FXML
    public void initialize() {

        expandableBox.setManaged(false);

        usernameLabel.setText(Attendee.currentUser.getUserName());

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

    @FXML
    private void expandBox(){
        expandableBox.setVisible(!expandableBox.isVisible());
        expandableBox.setManaged(!expandableBox.isManaged());
    }
}
