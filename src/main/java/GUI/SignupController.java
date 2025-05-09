package GUI;

import classes.Address;
import classes.Attendee;
import classes.Database;
import classes.Gender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.UnaryOperator;

import static classes.Database.*;

public class SignupController {

    @FXML
    private TextField usernameTextfield;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmTextField;

    @FXML
    private Label takenUsername;

    @FXML
    private Label requiredLabel;

    @FXML
    private Label passwordNotMatch;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private TextField postalCodeTextField;

    @FXML
    private DatePicker birthDate;

    @FXML
    private void initialize(){

        birthDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                // Disable dates before today
                if (item != null && item.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffcccc;"); // Optional: change color to indicate disabled date
                }
            }
        });

        genderComboBox.getItems().addAll(
          "Male",
          "Female"
        );
        countryComboBox.getItems().addAll(
                "Egypt",
                "KSA",
                "UAE"
        );
        cityComboBox.getItems().addAll(
                "Cairo",
                "Jeddah",
                "Dubai"
        );

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            // Allow digits and optionally a decimal point
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        postalCodeTextField.setTextFormatter(textFormatter);

        UnaryOperator<TextFormatter.Change> filter1 = change -> {
            String newText = change.getControlNewText();
            // Allow digits and optionally a decimal point
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter1 = new TextFormatter<>(filter);
        phoneTextField.setTextFormatter(textFormatter1);
    }

    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {

        requiredLabel.setVisible(false);
        takenUsername.setVisible(false);
        passwordNotMatch.setVisible(false);

        if (usernameTextfield.getText().isEmpty() || genderComboBox.getValue() == null || countryComboBox.getValue() == null || cityComboBox.getValue() == null || passwordTextField.getText().isEmpty() || confirmTextField.getText().isEmpty() || phoneTextField.getText().isEmpty() || birthDate.getValue() == null){
            requiredLabel.setVisible(true);
            return;
        }

        for (Attendee totalAttendee : totalAttendees) {
            if (usernameTextfield.getText().equals(totalAttendee.getUserName())) {
                takenUsername.setVisible(true);
                usernameTextfield.setText("");
                return;
            }
        }
        for (classes.Admin admin : admins) {
            if (usernameTextfield.getText().equals(admin.getUserName())) {
                takenUsername.setVisible(true);
                usernameTextfield.setText("");
                return;
            }
        }
        for (classes.Organizer organizer : organizers) {
            if (usernameTextfield.getText().equals(organizer.getUserName())) {
                takenUsername.setVisible(true);
                usernameTextfield.setText("");
                return;
            }
        }

        if (!confirmTextField.getText().equals(passwordTextField.getText())){
            passwordNotMatch.setVisible(true);
            confirmTextField.setText("");
            return;
        }


        Attendee attendee = new Attendee();

        Gender gender = Gender.MALE;
        if (genderComboBox.getValue().equals("Female")){
            gender = Gender.FEMALE;
        }

        Date birthdate = Date.from(Instant.from(birthDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        String country = countryComboBox.getValue();
        String city = cityComboBox.getValue();
        long postalCode = Long.valueOf(postalCodeTextField.getText());
        String street = streetTextField.getText();
        Address address = new Address(country, city, street, postalCode);

        String phoneNumber = phoneTextField.getText();

        System.out.println(usernameTextfield.getText() + ": " + passwordTextField.getText());

        attendee.signup(usernameTextfield.getText(), gender, birthdate, address, phoneNumber, passwordTextField.getText());

        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
