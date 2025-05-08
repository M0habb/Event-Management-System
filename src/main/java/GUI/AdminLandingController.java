package GUI;

import classes.Admin;
import classes.Attendee;
import classes.User;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.lang.classfile.Label;

import javafx.scene.control.Labeled;

import static classes.Admin.*;


public class AdminLandingController {





    @FXML
    private void handleSignout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    @FXML

    private void handleShowRooms(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/showRooms.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }


    @FXML
    private void handleShowEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/showEvents.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    private void handleShowAttendees(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/showAttendees.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    private void handleShowOrganizers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/showOrganizers.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }


    @FXML
    private void handleManageCategories(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/manageCategories.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }






}
