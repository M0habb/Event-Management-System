package GUI;

import classes.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

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
    private TextField priceTextField;
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
    @FXML
    private Button delete;
    @FXML
    private TextField nameTextField;
    @FXML
    private DatePicker date;
    @FXML
    private Label L;
    @FXML
    private Hyperlink hide;
    @FXML
    private ComboBox<String> rooms;

    private String text;

    @FXML
    private void initialize(){

        rooms.setVisible(false);
        priceTextField.setVisible(false);

        for (Room room : Database.rooms){
            if (room.getAvailable()) {
                rooms.getItems().add(room.getRoomName());
            }
        }

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



                if (Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()).after(event.getEventDate())){
                    delete.setVisible(false);
                    delete.setDisable(true);
                    hide.setVisible(false);
                    L.setVisible(false);
                }else {
                    roomText.setOnMouseClicked(x -> {
                        roomText.setVisible(false);
                        for (Room room : Database.rooms){
                            if (room.getAvailable()) {
                                rooms.getItems().add(room.getRoomName());
                            }
                        }
                        rooms.setVisible(true);
                        rooms.requestFocus();
                    });

                    rooms.setOnAction(x -> {
                        for (Room room : Database.rooms){
                            if (room.getRoomName().equals(roomText.getText())){
                                room.setAvailable(true);
                            }
                            if (room.getRoomName().equals(rooms.getValue())){
                                for (Event e : Database.events){
                                    if (e.getEventName().equals(nameText.getText())){
                                        e.setRoom(room);
                                        roomText.setText(room.getRoomName());
                                        locationText.setText(room.getAddress().toString());
                                    }
                                }
                            }
                        }
                        roomText.setVisible(true);
                        rooms.setVisible(false);
                    });

                    nameText.setOnMouseClicked(x -> {
                        nameTextField.setText(nameText.getText());
                        nameText.setVisible(false);
                        nameTextField.setVisible(true);
                        nameTextField.requestFocus();
                    });

                    nameTextField.setOnAction(x -> {
                        String ogName = nameText.getText();
                        nameText.setText(nameTextField.getText());
                        for (Event e : Database.events){
                            if (e.getEventName().equals(ogName)){
                                e.setEventName(nameText.getText());
                            }
                        }
                        nameTextField.setVisible(false);
                        nameText.setVisible(true);
                    });

                    nameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                        if (!newVal) {
                            nameText.setText(nameTextField.getText());
                            nameTextField.setVisible(false);
                            nameText.setVisible(true);
                        }
                    });

                    priceText.setOnMouseClicked(x -> {
                        priceTextField.setText(priceText.getText());
                        priceText.setVisible(false);
                        priceTextField.setVisible(true);
                        priceTextField.requestFocus();
                    });

                    priceTextField.setOnAction(x -> {
                        priceText.setText(priceTextField.getText());
                        for (Event e : Database.events){
                            if (e.getEventName().equals(nameText.getText())){
                                String newFees = priceText.getText().substring(13);
                                e.setFees(Double.parseDouble(newFees));
                            }
                        }
                        priceTextField.setVisible(false);
                        priceText.setVisible(true);
                    });

                    priceTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                        if (!newVal) {
                            priceText.setText(priceTextField.getText());
                            priceTextField.setVisible(false);
                            priceText.setVisible(true);
                        }
                    });
                }

                Date date1 = event.getEventDate();
                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd | h:mm a, yyyy");
                String formattedDate = formatter.format(date1);
                dateText.setText(formattedDate);
                date.setValue(LocalDate.ofInstant(date1.toInstant(), ZoneId.systemDefault()));

                roomText.setText(event.getRoom().getRoomName());

                priceText.setText(priceText.getText() + event.getFees());

                organizerText.setText(event.getOrganizer().getUserName());

                noAttendeesText.setText(noAttendeesText.getText() + event.getNumberofAttendees());

                totalProfitText.setText(totalProfitText.getText() + Double.toString(event.getNumberofAttendees() * event.getFees()));

                locationText.setText(event.getRoom().getAddress().toString());

                switch (event.getCategory().getType()){
                    case MUSIC:
                        Image image = new Image(getClass().getResourceAsStream("/resources/images/img_3.png"));
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

    @FXML
    private void handleDelete(ActionEvent event) throws IOException{

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You are about to delete this event. This process is irreversible. Do you want to proceed?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Database.events.removeIf(eventt -> eventt.getEventName().equals(nameText.getText()));
            Parent root = FXMLLoader.load(getClass().getResource("/resources/organizerLanding.fxml"));

            Scene scene = new Scene(root, 1142, 642);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }
    }

    @FXML
    private void hideL() {
        L.setVisible(false);
        hide.setVisible(false);
    }
}
