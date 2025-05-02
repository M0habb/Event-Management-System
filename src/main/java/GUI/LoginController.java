package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;
    
    @FXML
    private void handleLogin(){
        System.out.println(usernameField.getText());
    }
}
