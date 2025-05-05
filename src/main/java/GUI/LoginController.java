package GUI;

import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class 5LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label invalidLogin;
    @FXML
    private PasswordField hiddenPasswordField;
    @FXML
    private Hyperlink showPasswordHyperlink;

    private boolean passwordVisible = false;

    @FXML
    private void initialize(){
        passwordField.textProperty().bindBidirectional(hiddenPasswordField.textProperty());
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {

        if (usernameField.getText().isBlank() || passwordField.getText().isBlank()){
            invalidLogin.setText("Username/Password Required.");
            invalidLogin.setVisible(true);
            return;
        }
        int loginStatus = User.login(usernameField.getText(), passwordField.getText());

        if (loginStatus == 0){
            invalidLogin.setText("Incorrect username or password!");
            invalidLogin.setVisible(true);
            return;
        }

        if (loginStatus == 1){
            Parent root = FXMLLoader.load(getClass().getResource("/resources/adminLanding.fxml"));

            Scene scene = new Scene(root, 1142, 642);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }

        if (loginStatus == 2){
            Parent root = FXMLLoader.load(getClass().getResource("/resources/organizerLanding.fxml"));

            Scene scene = new Scene(root, 1142, 642);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }

        if (loginStatus == 3){
            Parent root = FXMLLoader.load(getClass().getResource("/resources/attendeeLanding.fxml"));

            Scene scene = new Scene(root, 1142, 642);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }
    }


    @FXML
    private void handleSignup(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/resources/signup.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void togglePasswordVisibility(){

        if (passwordField.getText().isBlank()) return;

        passwordVisible = !passwordVisible;


        passwordField.setVisible(passwordVisible);
        hiddenPasswordField.setVisible(!passwordVisible);

        showPasswordHyperlink.setText(passwordVisible ? "Hide Password" : "Show Password");
    }
}
