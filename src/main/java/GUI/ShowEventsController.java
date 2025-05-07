package GUI;

import classes.Database;
import classes.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

public class ShowEventsController {

    @FXML
    private TableView<Event> eventsTable;

    @FXML
    private TableColumn<Event, String> nameColumn;

    @FXML
    private TableColumn<Event, String> dateColumn;

    @FXML
    private TableColumn<Event, String> outdoorColumn;

    @FXML
    private TableColumn<Event, String> roomColumn;

    @FXML
    private TableColumn<Event, String> categoryColumn;

    @FXML
    private TableColumn<Event, String> organizerColumn;

    @FXML
    public void initialize() {
        // Simple fields
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        dateColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEventDate().toString()));
        outdoorColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getOutdoors() ? "Yes" : "No"));
        roomColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getRoomNum())));

        // Nested/complex fields
        categoryColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCategory().getType().toString()));
        organizerColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getOrganizer().getUserName()));

        // Populate the table
        ObservableList<Event> events = FXCollections.observableArrayList(Database.events);
        eventsTable.setItems(events);
    }
}
