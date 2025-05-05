package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ViewEventsController {
    boolean isYellow = false;

    @FXML
    private Button musicButton;

    @FXML
    private void initialize(){

    }

    @FXML
    private void handleMusicButton(){

        if (!isYellow){
            musicButton.setStyle("-fx-background-color: #f2dfa7;");
        }else musicButton.setStyle("-fx-background-color: transparent;");

        isYellow = !isYellow;
        System.out.println("Button Works!");

    }
}
