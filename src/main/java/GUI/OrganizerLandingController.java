package GUI;

<<<<<<< HEAD
import classes.Organizer;
=======
import classes.Database;
>>>>>>> 2642b5467ac3ddba414d4f1cde47ad4ec9c1fe4c
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
<<<<<<< HEAD
import javafx.scene.text.Text;
=======
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
>>>>>>> 2642b5467ac3ddba414d4f1cde47ad4ec9c1fe4c
import javafx.stage.Stage;

import java.io.IOException;

public class OrganizerLandingController {
<<<<<<< HEAD
=======
    @FXML
    private ScrollPane scrollpane;

>>>>>>> 2642b5467ac3ddba414d4f1cde47ad4ec9c1fe4c
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

    @FXML
    private void handleclick(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/EventViewer.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    private void handleUpcomingEvents() {
        HBox rootH = (HBox) scrollpane.getContent();
        for (int i = 0; i < Database.events.size(); i++) {
            if (User.currentUser == Database.events.get(i).getOrganizer()) {

                try {
                    rootH = FXMLLoader.load(getClass().getResource("/resources/hboxEvent.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Label eventNameLabel = (Label) rootH.getChildren().get(1);
                eventNameLabel.setText(Database.tickets.get(i).getEventName());
            }
        }
    }
}




