package GUI;

import classes.Organizer;
import classes.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowOrganizersController {

    @FXML
    private TableView<Organizer> organizersTable;

    @FXML
    private TableColumn<Organizer, String> usernameColumn;

    @FXML
    private TableColumn<Organizer, Integer> eventsCreatedColumn;

    @FXML
    public void initialize() {
        usernameColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getUserName())
        );

        eventsCreatedColumn.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getEventsCreated().size()).asObject()
        );

        organizersTable.getItems().setAll(Database.organizers);
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/adminLanding.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
