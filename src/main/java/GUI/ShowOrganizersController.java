package GUI;

import classes.Organizer;
import classes.Database;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ShowOrganizersController {

    @FXML
    private TableView<Organizer> organizersTable;

    @FXML
    private TableColumn<Organizer, String> usernameColumn;

    @FXML
    private TableColumn<Organizer, Integer> eventsCreatedColumn;

    @FXML
    public void initialize() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        eventsCreatedColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getEventsCreated().size()
                ).asObject());

        List<Organizer> organizers = Database.organizers;
        organizersTable.getItems().addAll(organizers);
    }
}
