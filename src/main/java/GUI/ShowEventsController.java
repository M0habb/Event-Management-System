package GUI;

import classes.Database;
import classes.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.text.SimpleDateFormat;

public class ShowEventsController {

    @FXML private TableView<Event> showEvents;
    @FXML private TableColumn<Event, String> nameColumn;
    @FXML private TableColumn<Event, String> dateColumn;
    @FXML private TableColumn<Event, Integer> roomColumn;
    @FXML private TableColumn<Event, String> categoryColumn;

    private ObservableList<Event> eventList;

    @FXML
    public void initialize() {
        eventList = FXCollections.observableArrayList(Database.events);
        showEvents.setItems(eventList);

        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventName()));

        dateColumn.setCellValueFactory(data -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return new SimpleStringProperty(sdf.format(data.getValue().getEventDate()));
        });

        roomColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getRoomNum()));

        categoryColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCategory().getType().toString()));
    }
}
