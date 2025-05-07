package GUI;

import classes.Address;
import classes.Database;
import classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProceedToCheckoutController {
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private ;
    @FXML
    private
    private void displayTicketsBought(){
        VBox rootV= (VBox)scrollpane.getContent();
        for (int i = 0; i < Database.tickets.size(); i++){
            if(User.currentUser== Database.tickets.get(i).getOwner()) {
                HBox rootH=(HBox)rootV.getChildren().get(0);
                Label eventNameLabel=(Label)rootH.getChildren().get(0);
            }

            }



    }
}
