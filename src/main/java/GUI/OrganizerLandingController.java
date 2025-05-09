package GUI;


import classes.CategoryType;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label venueLabel;
    @FXML
    private Label priceLabel;

    @FXML
    private ListView listView;

    Organizer currentUser = (Organizer) User.currentUser;

    @FXML
    private void initialize() throws IOException {
        usernameLabel.setText(currentUser.getUserName());
        handleUpcomingEvents();
        HBox rootH = (HBox) scrollpane.getContent();
        rootH.setSpacing(50);
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

                Label mainLabel = FXMLLoader.load(getClass().getResource("/resources/hboxEvent.fxml"));
                mainLabel.setText(Database.events.get(i).getEventName());
                switch (Database.events.get(i).getCategory().getType()) {
                    case CategoryType.MUSIC:
                        Image image = new Image(getClass().getResourceAsStream("/resources/images/music1.png"));
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(181);
                        imageView.setFitHeight(160);
                        mainLabel.setGraphic(imageView);
                        break;
                    case CategoryType.SPORTS:
                        Image image1 = new Image(getClass().getResourceAsStream("/resources/images/sports.png"));
                        ImageView imageView1 = new ImageView(image1);
                        imageView1.setFitWidth(181);
                        imageView1.setFitHeight(160);
                        mainLabel.setGraphic(imageView1);
                        break;
                    case CategoryType.THEATER:
                        Image image2 = new Image(getClass().getResourceAsStream("/resources/images/theater.jpeg"));
                        ImageView imageView2 = new ImageView(image2);
                        imageView2.setFitWidth(181);
                        imageView2.setFitHeight(160);
                        mainLabel.setGraphic(imageView2);
                        break;
                    case CategoryType.CONFERENCE:
                        Image image3 = new Image(getClass().getResourceAsStream("/resources/images/conference.jpeg"));
                        ImageView imageView3 = new ImageView(image3);
                        imageView3.setFitWidth(181);
                        imageView3.setFitHeight(160);
                        mainLabel.setGraphic(imageView3);
                        break;
                }

                if (Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()).before(Database.events.get(i).getEventDate())) {
                    count++;
                    rootH.getChildren().add(mainLabel);

                    if (count == 1) {
                        Label main = mainLabel;
                        mainAnchorPane.getChildren().add(main);
                        nameLabel.setText(mainLabel.getText());
                        dateLabel.setText(Database.events.get(i).getEventDate().toString());
                        venueLabel.setText(Database.events.get(i).getRoom().getRoomName());
                        priceLabel.setText("Ticket Price: " + Database.events.get(i).getFees());
                    }

                }else {
                    listView.getItems().add(mainLabel);
                }
            }
        }
    }
}
