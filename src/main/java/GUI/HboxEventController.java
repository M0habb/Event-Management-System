package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HboxEventController {

    @FXML
    private Label eventNameLabel;

    @FXML
    private void handleClick(MouseEvent event) throws IOException{
        System.out.println("working");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/eventViewer.fxml"));
        Parent root = loader.load();

        EventViewerController eventViewerController = loader.getController();

        eventViewerController.setEventName(eventNameLabel.getText());

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
