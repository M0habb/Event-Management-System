package GUI;

import classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label loginFailed;
    
    @FXML
    private void handleLogin(){
        int loginStatus = User.login(usernameField.getText(), passwordField.getText());

        if (loginStatus == 0){

        }
    }
}
