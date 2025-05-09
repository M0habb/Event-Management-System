package GUI;

import classes.Attendee;

import classes.Organizer;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class EventViewerController {

    Organizer currentUser = (Organizer) User.currentUser;

    @FXML
    private Text usernameLabel;

    @FXML
    private ImageView background;

    @FXML
    private Text nameText;
    @FXML
    private Text dateText;
    @FXML
    private Text roomText;
    @FXML
    private Text priceText;
    @FXML
    private Text organizerText;
    @FXML
    private Text noAttendeesText;
    @FXML
    private Text totalProfitText;
    @FXML
    private Text locationText;

    private String eventName;

    @FXML
    private void initialize(){
        usernameLabel.setText(currentUser.getUserName());
        Image image = new Image(getClass().getResourceAsStream("/resources/images/music1.png"));
        background.setImage(image);
        background.setFitWidth(1154);
        background.setFitHeight(270);
        background.setPreserveRatio(false); // or true, depending on the need
        background.setSmooth(true);
        background.toBack();
        background.setOpacity(0.7);
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
        Parent root = FXMLLoader.load(getClass().getResource("/resources/organizerLanding.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
