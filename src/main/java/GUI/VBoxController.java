package GUI;

import classes.Attendee;
import classes.Database;
import classes.Event;
import classes.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VBoxController{

    @FXML private Label eventName;
    @FXML private Label eventDate1;
    @FXML private Button buyButton;
    @FXML private Label cartLabel;

    boolean ticketPurchased = false;

    @FXML private VBox expandableBox;

    @FXML
    private void initialize(){
        expandableBox.setManaged(false);
        cartLabel.setVisible(false);
    }

    @FXML
    private void expandBox(){
        expandableBox.setVisible(!expandableBox.isVisible());
        expandableBox.setManaged(!expandableBox.isManaged());
    }

    @FXML
    public void handleBuy(ActionEvent actionEvent){

        for (int i = 0; i < Database.tickets.size(); i++){
            if (Database.tickets.get(i).getOwner().equals(Attendee.currentUser) && Database.tickets.get(i).getEventName().equals(eventName.getText())){
                Database.tickets.remove(i);
                buyButton.setText("Buy Ticket!");
                cartLabel.setVisible(false);
                return;
            }
        }

        Event event = new Event();
        for (int i = 0; i < Database.events.size(); i++){
            if (eventName.getText().equals(Database.events.get(i).getEventName())){
                event = Database.events.get(i);
            }
        }
        Ticket ticket = new Ticket(event.getEventName(), (Attendee) Attendee.currentUser, event.getFees(), event.getEventDate());
        Database.tickets.add(ticket);
        buyButton.setText("Remove from cart.");
        cartLabel.setVisible(true);
    }
}
