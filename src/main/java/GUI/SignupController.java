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
import java.util.Date;
import java.util.function.UnaryOperator;

import static classes.Database.totalAttendees;

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
    private void handleSignUp(){

        if (usernameTextfield.getText().isEmpty() || genderComboBox.getValue() == null || countryComboBox.getValue() == null || cityComboBox.getValue() == null || passwordTextField.getText().isEmpty() || confirmTextField.getText().isEmpty() || phoneTextField.getText().isEmpty() || birthDate.getValue() == null){
            requiredLabel.setVisible(true);
            return;
        }

        for (int i = 0; i < totalAttendees.size(); i++){
            if (usernameTextfield.getText().equals(totalAttendees.get(i).getUserName())){
                takenUsername.setVisible(true);
                return;
            }
        }

        Attendee attendee = new Attendee();

        Gender gender = Gender.MALE;
        if (genderComboBox.getValue().equals("Male")){
            gender = Gender.MALE;
        }else if(genderComboBox.getValue().equals("Female")){
            gender = Gender.FEMALE;
        }

        Date birthdate = Date.from(Instant.from(birthDate.getValue()));

        String country = countryComboBox.getValue();
        String city = cityComboBox.getValue();
        long postalCode = Long.valueOf(postalCodeTextField.getText());
        String street = streetTextField.getText();
        Address address = new Address(country, city, street, postalCode);

        long phoneNumber = Long.valueOf(phoneTextField.getText());

        String password = passwordTextField.getText();

        attendee.signup(usernameTextfield.getText(), gender, birthdate, address, phoneNumber, password);


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
