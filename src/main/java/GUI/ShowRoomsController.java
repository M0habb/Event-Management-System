package GUI;

import classes.Event;
import classes.Room;
import classes.Database;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowRoomsController {

    @FXML
    private TableView<Room> roomsTable;

    @FXML
    private TableColumn<Room, Integer> roomNumColumn;

    @FXML
    private TableColumn<Room, String> sizeColumn;

    @FXML
    private TableColumn<Room, String> availableColumn;

    @FXML
    private TableColumn<Room, String> eventColumn;



    @FXML
    public void initialize() {
        // Setup how data is pulled from Room
        roomNumColumn.setCellValueFactory(new PropertyValueFactory<>("roomNum"));

        sizeColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSize().toString())
        );

        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        eventColumn.setCellValueFactory(cellData -> {
            for (int i = 0; i < Database.events.size(); i++){
                if (cellData.getValue().getRoomNum() == Database.events.get(i).getRoomNum()){
                    return new SimpleStringProperty(Database.events.get(i).getEventName());
                }
            }
            return new SimpleStringProperty("N/A");
        });

        // Fill the table with data
        ObservableList<Room> roomList = FXCollections.observableArrayList(Database.rooms);
        roomsTable.setItems(roomList);
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
