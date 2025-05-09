package GUI;

import classes.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private TableColumn<Room, String> addressColumn;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField sizeTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private TextField countryTextField;
    @FXML
    private Label required;

    private ObservableList<Room> roomList = FXCollections.observableArrayList(Database.rooms);

    @FXML
    private CheckBox outdoorsCheckBox;

    @FXML
    public void initialize() {
        roomNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        sizeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSize())));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        eventColumn.setCellValueFactory(cellData -> {
            for (int i = 0; i < Database.events.size(); i++) {
                if (cellData.getValue().getRoomName().equals(Database.events.get(i).getRoom().getRoomName())) {
                    return new SimpleStringProperty(Database.events.get(i).getEventName());
                }
            }
            return new SimpleStringProperty("N/A");
        });
        addressColumn.setCellValueFactory(cellData -> {
            Address address = cellData.getValue().getAddress();
            String formattedAddress = address.street + ", " + address.city + ", " + address.country + ". " + address.postalCode;
            return new SimpleStringProperty(formattedAddress);
        });
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
        if (nameTextField.getText().isEmpty() || sizeTextField.getText().isEmpty() ||
                streetTextField.getText().isEmpty() || cityTextField.getText().isEmpty() ||
                postalCodeTextField.getText().isEmpty() || countryTextField.getText().isEmpty()) {
            required.setVisible(true);
            return;
        }
        try {
            String name = nameTextField.getText();
            int size = Integer.parseInt(sizeTextField.getText());
            String street = streetTextField.getText();
            String city = cityTextField.getText();
            long postalCode = Long.parseLong(postalCodeTextField.getText());
            String country = countryTextField.getText();
            boolean outdoors = outdoorsCheckBox.isSelected();
            Address address = new Address(country, city, street, postalCode);
            Room room = new Room(name, size, true, address, outdoors);
            Database.rooms.add(room);
            roomList = FXCollections.observableArrayList(Database.rooms);
            roomsTable.setItems(roomList);
            roomsTable.refresh();
            required.setVisible(false);
            nameTextField.setText("");
            sizeTextField.setText("");
            streetTextField.setText("");
            cityTextField.setText("");
            postalCodeTextField.setText("");
            countryTextField.setText("");
            outdoorsCheckBox.setSelected(false);
        } catch (NumberFormatException e) {
            required.setText("Size and postal code must be numbers");
            required.setVisible(true);
        }
    }
}
