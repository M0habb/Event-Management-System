package GUI;

import classes.Admin;
import classes.Attendee;
import classes.Database;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

public class ShowAttendeesController {

    @FXML private TableView<Attendee> showAttendees;
    @FXML private TableColumn<Attendee, String> usernameColumn;
    @FXML private TableColumn<Attendee, String> balanceColumn;

    @FXML
    public void initialize() {
        // Column binding
        usernameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getUserName())
        );

        balanceColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        String.valueOf(cellData.getValue().getWallet().getBalance())
                )
        );

        // Fill table with all attendees from the database
        showAttendees.setItems(FXCollections.observableArrayList(Database.totalAttendees));
    }
}
