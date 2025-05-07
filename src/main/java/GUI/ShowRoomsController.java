package GUI;

import classes.Room;
import classes.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowRoomsController {

    @FXML
    private TableView<Room> roomsTable;

    @FXML
    private TableColumn<Room, Integer> roomNumColumn;

    @FXML
    private TableColumn<Room, String> sizeColumn;



    @FXML
    public void initialize() {
        // Setup how data is pulled from Room
        roomNumColumn.setCellValueFactory(new PropertyValueFactory<>("roomNum"));

        sizeColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSize().toString())
        );

        

        // Fill the table with data
        ObservableList<Room> roomList = FXCollections.observableArrayList(Database.rooms);
        roomsTable.setItems(roomList);
    }
}
