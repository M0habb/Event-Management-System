package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VBoxController{

    @FXML private Label eventName;
    @FXML private Label eventDate1;
    @FXML private Button buyButton;

    boolean ticketPurchased = false;

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

    @FXML
    public void handleBuy(ActionEvent event){

        if (!ticketPurchased){
            ticketPurchased = true;
            buyButton.setText("Ticket Added to Cart!");
        }else return;

        ViewEventsController.handleBuy(eventName);
    }
}
