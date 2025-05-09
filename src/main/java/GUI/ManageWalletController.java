package GUI;

import classes.Attendee;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ManageWalletController {
    public Button Back;
    @FXML
    private TextField amountField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label notification;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Label currentBalanceLabel;
    @FXML
    private void initialize(){
        usernameLabel.setText(Attendee.currentUser.getUserName());
        notification.setVisible(false);
        currentBalanceLabel.setText("Your Current Balance is : "+ User.currentUser.getWallet().getBalance());
    }
    @FXML
    private void handleSignout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
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
    @FXML
    private void handlePay(){
        double amount = Double.valueOf(amountField.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("You are about to pay " + amount + ". Do you want to proceed?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User clicked OK
                if(amount >0) {
                    User.currentUser.getWallet().addBalance(amount);
                    notification.setVisible(true);
                    notification.setText("Transaction Successful");
                    currentBalanceLabel.setText("Your Current Balance is : " + User.currentUser.getWallet().getBalance());
                }else notification.setText("*Invalid amount entered");




            }
    }

}
