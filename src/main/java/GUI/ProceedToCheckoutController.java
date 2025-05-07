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

    private void displayTicketsBought(){
        VBox rootV = (VBox)scrollpane.getContent();
        for (int i = 0; i < Database.tickets.size(); i++){
            if(User.currentUser== Database.tickets.get(i).getOwner()) {

                HBox rootH;
                try {
                    rootH = FXMLLoader.load(getClass().getResource("/resources/hbox_layout.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Label eventNameLabel=(Label)rootH.getChildren().get(0);
                eventNameLabel.setText(Database.tickets.get(i).getEventName());
                Label eventDateLabel=(Label)rootH.getChildren().get(1);
                Date date = Database.tickets.get(i).getDate();
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
                String dateString = formatter.format(date);
                eventDateLabel.setText(dateString);
                Label eventPriceLabel=(Label)rootH.getChildren().get(2);
                eventPriceLabel.setText(String.valueOf(Database.tickets.get(i).getFees()));

                rootV.getChildren().add(rootH);


            }

            }



    }
}
