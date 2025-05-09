package GUI;

import classes.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Label attendeesLabel;

    private String text;

    @FXML
    private void initialize(){

        if (text != null){
            nameText.setText(text);
        }
        System.out.println(nameText.getText());

        usernameLabel.setText(currentUser.getUserName());

        Platform.runLater(this::setText);

        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollpane.setPannable(true);
    }

    private void setText(){
        for (Event event : Database.events){
            if (event.getEventName().equals(nameText.getText())){

                Date date = event.getEventDate();
                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd | h:mm a, yyyy");
                String formattedDate = formatter.format(date);
                dateText.setText(formattedDate);

                roomText.setText(event.getRoom().getRoomName());

                priceText.setText(priceText.getText() + event.getFees());

                organizerText.setText(event.getOrganizer().getUserName());

                noAttendeesText.setText(noAttendeesText.getText() + event.getNumberofAttendees());

                totalProfitText.setText(totalProfitText.getText() + Double.toString(event.getNumberofAttendees() * event.getFees()));

                locationText.setText(event.getRoom().getAddress().toString());

                switch (event.getCategory().getType()){
                    case MUSIC:
                        Image image = new Image(getClass().getResourceAsStream("/resources/images/music1.png"));
                        background.setImage(image);
                        break;
                    case SPORTS:
                        Image image2 = new Image(getClass().getResourceAsStream("/resources/images/sports.png"));
                        background.setImage(image2);
                        break;
                    case THEATER:
                        Image image3 = new Image(getClass().getResourceAsStream("/resources/images/theater.jpeg"));
                        background.setImage(image3);
                        break;
                    case CONFERENCE:
                        Image image4 = new Image(getClass().getResourceAsStream("/resources/images/conference.jpeg"));
                        background.setImage(image4);
                        break;
                }
                int i = 0;
                for (Attendee attendee : event.getAttendees()){
                    attendeesLabel.setText(attendeesLabel.getText() + ", " + attendee.getUserName());
                }

                if (attendeesLabel.getText().length() > 4){
                    attendeesLabel.setText(attendeesLabel.getText().substring(2, attendeesLabel.getText().length()));
                }

                background.setFitWidth(1154);
                background.setFitHeight(270);
                background.setPreserveRatio(false); // or true, depending on the need
                background.setSmooth(true);
                background.toBack();
                background.setOpacity(0.7);
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
        Parent root = FXMLLoader.load(getClass().getResource("/resources/organizerLanding.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void setEventName(String eventName) {
        text = eventName;
        if (text != null){
            nameText.setText(text);
        }
    }
}
