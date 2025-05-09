package GUI;

import classes.Attendee;
import classes.Database;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowAttendeesController {

    @FXML private TableView<Attendee> showAttendees;
    @FXML private TableColumn<Attendee, String> usernameColumn;
    @FXML private TableColumn<Attendee, String> balanceColumn;
    @FXML private TableColumn<Attendee, String> genderColumn;
    @FXML private TableColumn<Attendee, String> addressColumn;
    @FXML private TableColumn<Attendee, String> birthdateColumn;
    @FXML private TableColumn<Attendee, String> phoneColumn;


    @FXML
    public void initialize() {
        usernameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserName()));
        balanceColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getWallet().getBalance())));
        genderColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGender().toString()));
        addressColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress().toString()));
        birthdateColumn.setCellValueFactory(data -> {
            Date date = data.getValue().getBirthDate();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = formatter.format(date);
            return new SimpleStringProperty(formattedDate);
        });
        phoneColumn.setCellValueFactory(data -> new SimpleStringProperty(Long.toString(data.getValue().getPhoneNumber())));

        showAttendees.setItems(FXCollections.observableArrayList(Database.totalAttendees));
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
