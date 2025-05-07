package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VBoxController {

    @FXML private Label eventName1;
    @FXML private Label eventDate1;



    @FXML private VBox expandableBox;

    @FXML
    private void initialize(){
        expandableBox.setManaged(false);
    }

    @FXML
    private void expandBox(){
        expandableBox.setVisible(!expandableBox.isVisible());
        expandableBox.setManaged(!expandableBox.isManaged());
    }
}
