package GUI;

import classes.Category;
import classes.CategoryType;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowRoomsController {

    @FXML
    private TableView<Room> roomsTable;

    @FXML
    private TableColumn<Room, String> roomNameColumn;

    @FXML
    private TableColumn<Room, String> sizeColumn;

    @FXML
    private TableColumn<Room, String> availableColumn;

    @FXML
    private TableColumn<Room, String> eventColumn;

    @FXML
    private TextField nameTextField;
    @FXML
    private Label required;

    private ObservableList<Room> roomList = FXCollections.observableArrayList(Database.rooms);

    @FXML private TextField sizeTextField;

    @FXML private TextField addressTextField;


    @FXML
    public void initialize() {
        // Setup how data is pulled from Room
        roomNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));

        sizeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getSize()))
        );

        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        eventColumn.setCellValueFactory(cellData -> {
            for (int i = 0; i < Database.events.size(); i++) {
                if (cellData.getValue().getRoomName() == Database.events.get(i).getRoom().getRoomName()) {
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

    @FXML
    private void handleAdd() {
        // Check for empty input
        if (nameTextField.getText().isEmpty() ||
                sizeTextField.getText().isEmpty() ||
                addressTextField.getText().isEmpty()) {

            required.setText("All fields are required");
            required.setVisible(true);
            return;
        }

        try {
            String name = nameTextField.getText();
            int size = Integer.parseInt(sizeTextField.getText());
            String address = addressTextField.getText();

            Room room = new Room(name, size, true); // true = available

            Database.rooms.add(room);

            // Refresh table
            roomList = FXCollections.observableArrayList(Database.rooms);
            roomsTable.setItems(roomList);
            roomsTable.refresh();

            // Reset UI
            required.setVisible(false);
            nameTextField.setText("");
            sizeTextField.setText("");
            addressTextField.setText("");

        } catch (NumberFormatException e) {
            required.setText("Size must be a valid number");
            required.setVisible(true);
        }
    }


}
