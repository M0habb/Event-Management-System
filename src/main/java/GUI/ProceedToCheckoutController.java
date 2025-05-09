package GUI;

import classes.Address;
import classes.Attendee;
import classes.Database;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import static GUI.ViewEventsController.currentUser;
public class ProceedToCheckoutController {
    @FXML private Label totalLabel;

    @FXML
    private Label EmptyCart;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label notification;
    @FXML
    private double sum;;
    @FXML
    private void initialize(){
        EmptyCart.setVisible(false);
        usernameLabel.setText(Attendee.currentUser.getUserName());
        displayTicketsBought();
        totalLabel.setText("Total: $ "+String.valueOf(sum));
        notification.setVisible(false);
    }

    @FXML
        private ScrollPane scrollpane;


    @FXML

    private void displayTicketsBought(){
        VBox rootV = (VBox) scrollpane.getContent();
        rootV.getChildren().clear();
        int ticketCount = 0;
        for (int i = 0; i < Database.tickets.size(); i++){
            if(User.currentUser == Database.tickets.get(i).getOwner()) {
                ticketCount++;
                HBox rootH;
                try {
                    rootH = FXMLLoader.load(getClass().getResource("/resources/hbox_layout.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Label eventNameLabel = (Label) rootH.getChildren().get(1);
                eventNameLabel.setText(Database.tickets.get(i).getEventName());
                Label eventDateLabel = (Label) rootH.getChildren().get(2);
                Date date = Database.tickets.get(i).getDate();
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
                String dateString = "";
                if (date != null){
                    dateString = formatter.format(date);
                }
                eventDateLabel.setText(dateString);
                Label eventPriceLabel = (Label)rootH.getChildren().get(3);
                eventPriceLabel.setText("$"+String.valueOf(Database.tickets.get(i).getFees()));

                rootV.getChildren().add(rootH);
                sum = sum+Database.tickets.get(i).getFees();


            }

        }

        if (ticketCount == 0) EmptyCart.setVisible(true);
    }

    @FXML
    private void handleSignout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    //make remove ticket button
    //make label not visible in back
    @FXML
    private void handlePayByWallet(){

        if (sum == 0) return;

        VBox rootV = (VBox) scrollpane.getContent();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You are about to pay " + sum + ". Do you want to proceed?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            // User clicked OK
            notification.setVisible(true);
            if(Attendee.currentUser.getWallet().deductBalance(sum)){
                notification.setText("*Transaction Successful");
                rootV.getChildren().clear();
                sum = 0;
                EmptyCart.setVisible(true);
                totalLabel.setText(Double.toString(sum));
            }
            else{
                notification.setText("*Insufficient Funds");
            }

        }
    }
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/attendeeLanding.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        notification.setVisible(false);

    }

}
