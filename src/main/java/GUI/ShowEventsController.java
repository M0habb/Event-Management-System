package GUI;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShowEventsController {

    @FXML private ScrollPane scrollpane;

    @FXML private Button musicCategory;
    @FXML private Button sportsCategory;
    @FXML private Button theaterCategory;
    @FXML private Button conferenceCategory;
    @FXML private Button otherCategory;
    @FXML private Label usernameLabel;

    static User currentUserU = Admin.currentUser;
    static Admin currentUser = (Admin) currentUserU;

    private List<Button> categoryButtons;
    private Map<Button, String> categoryButtonColors;

    @FXML
    public void initialize() {

        displayEvents();

        usernameLabel.setText(currentUser.getUserName());

        categoryButtons = Arrays.asList(musicCategory, sportsCategory, theaterCategory, conferenceCategory, otherCategory);

        // Assign each button its unique color
        categoryButtonColors = new HashMap<>();
        categoryButtonColors.put(musicCategory, "#f1c40f");
        categoryButtonColors.put(sportsCategory, "#16a085");
        categoryButtonColors.put(theaterCategory, "#3498db");
        categoryButtonColors.put(conferenceCategory, "#9b59b6");
        categoryButtonColors.put(otherCategory, "#95a5a6");

        VBox rootV = (VBox) scrollpane.getContent();
        rootV.setSpacing(50);


    }

    private void displayEvents(){
        VBox rootV = (VBox) scrollpane.getContent();
        for (int i =0; i < Database.events.size(); i++){
            VBox newVBox;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vbox_layout.fxml"));
        newVBox = loader.load();
        VBoxController vBoxController = loader.getController();
        vBoxController.setDisableButton(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Node hbox1 = newVBox.getChildren().get(0);
            HBox hbox = (HBox) hbox1;
            Label eventName = (Label) hbox.getChildren().get(0);
            Label eventDate = (Label) hbox.getChildren().get(1);

            eventName.setText(Database.events.get(i).getEventName());
            Date date = Database.events.get(i).getEventDate();
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
            String dateString = formatter.format(date);
            eventDate.setText(dateString);

            VBox innerV = (VBox) newVBox.getChildren().get(1);
            Label eventLoc = (Label) innerV.getChildren().get(0);
            Label eventOrganizer = (Label) innerV.getChildren().get(1);
            Label eventOutdoors = (Label) innerV.getChildren().get(2);
            Label eventActivities = (Label) innerV.getChildren().get(3);
            eventActivities.setText("-" + Database.events.get(i).getCategory().getName() + " Event");
            HBox bottomHBox = (HBox) innerV.getChildren().get(4);
            Label eventFees = (Label) bottomHBox.getChildren().get(0);

            Address address = Database.events.get(i).getRoom().getAddress();

            eventLoc.setText(eventLoc.getText() + " " + address.country + ", " + address.city + ", " + address.street + ".");
            eventOrganizer.setText(eventOrganizer.getText() + " " +  Database.events.get(i).getOrganizer().getUserName() + ".");
            String outdoors = "outdoors.";
            if (!Database.events.get(i).getOutdoors()) outdoors = "indoors.";
            eventOutdoors.setText(eventOutdoors.getText() +  outdoors);
            eventFees.setText(eventFees.getText() + " " +  Database.events.get(i).getFees() + ".");

            rootV.getChildren().add(newVBox);
        }
    }
    private boolean toggleBackground(Button clickedButton) {
        boolean colored = true;
        String color = categoryButtonColors.get(clickedButton);
        if (!clickedButton.getStyle().contains(color)){
            colored = false;
        }
        for (Button b : categoryButtons) {
            b.setStyle("-fx-background-color: transparent;");
        }

        if (!colored) {
            clickedButton.setStyle("-fx-background-color: " + color + ";");
            return true;
        }
        return false;
    }

    @FXML
    private void handleMusicCategory(){
        VBox rootV = (VBox) scrollpane.getContent();
        boolean toggle = toggleBackground(musicCategory);

        if (toggle){

            rootV.getChildren().clear();

            for (int i =0; i < Database.events.size(); i++){

                if(Database.events.get(i).getCategory().getType().equals(CategoryType.MUSIC)){
                    VBox newVBox;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vbox_layout.fxml"));
        newVBox = loader.load();
        VBoxController vBoxController = loader.getController();
        vBoxController.setDisableButton(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Node hbox1 = newVBox.getChildren().get(0);
                    HBox hbox = (HBox) hbox1;
                    Label eventName = (Label) hbox.getChildren().get(0);
                    Label eventDate = (Label) hbox.getChildren().get(1);

                    eventName.setText(Database.events.get(i).getEventName());
                    Date date = Database.events.get(i).getEventDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
                    String dateString = formatter.format(date);
                    eventDate.setText(dateString);

                    VBox innerV = (VBox) newVBox.getChildren().get(1);
                    Label eventLoc = (Label) innerV.getChildren().get(0);
                    Label eventOrganizer = (Label) innerV.getChildren().get(1);
                    Label eventOutdoors = (Label) innerV.getChildren().get(2);
                    Label eventActivities = (Label) innerV.getChildren().get(3);
                        eventActivities.setText("-" + Database.events.get(i).getCategory().getName() + " Event");
                    HBox bottomHBox = (HBox) innerV.getChildren().get(4);
                    Label eventFees = (Label) bottomHBox.getChildren().get(0);

                    Address address = Database.events.get(i).getRoom().getAddress();

                    eventLoc.setText(eventLoc.getText() + " " + address.country + ", " + address.city + ", " + address.street + ".");
                    eventOrganizer.setText(eventOrganizer.getText() + " " +  Database.events.get(i).getOrganizer().getUserName() + ".");
                    String outdoors = "outdoors.";
                    if (!Database.events.get(i).getOutdoors()) outdoors = "indoors.";
                    eventOutdoors.setText(eventOutdoors.getText() +  outdoors);
                    eventFees.setText(eventFees.getText() + " " +  Database.events.get(i).getFees() + ".");

                    rootV.getChildren().add(newVBox);
                }
            }
        }else {
            System.out.println("I am working");
            displayEvents();
        }
    }

    @FXML
    private void handleSportsCategory(){
        VBox rootV = (VBox) scrollpane.getContent();
        boolean toggle = toggleBackground(sportsCategory);

        if (toggle){

            rootV.getChildren().clear();

            for (int i =0; i < Database.events.size(); i++){

                if(Database.events.get(i).getCategory().getType().equals(CategoryType.SPORTS)){
                    VBox newVBox;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vbox_layout.fxml"));
        newVBox = loader.load();
        VBoxController vBoxController = loader.getController();
        vBoxController.setDisableButton(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Node hbox1 = newVBox.getChildren().get(0);
                    HBox hbox = (HBox) hbox1;
                    Label eventName = (Label) hbox.getChildren().get(0);
                    Label eventDate = (Label) hbox.getChildren().get(1);

                    eventName.setText(Database.events.get(i).getEventName());
                    Date date = Database.events.get(i).getEventDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
                    String dateString = formatter.format(date);
                    eventDate.setText(dateString);

                    VBox innerV = (VBox) newVBox.getChildren().get(1);
                    Label eventLoc = (Label) innerV.getChildren().get(0);
                    Label eventOrganizer = (Label) innerV.getChildren().get(1);
                    Label eventOutdoors = (Label) innerV.getChildren().get(2);
                    Label eventActivities = (Label) innerV.getChildren().get(3);
                        eventActivities.setText("-" + Database.events.get(i).getCategory().getName() + " Event");
                    HBox bottomHBox = (HBox) innerV.getChildren().get(4);
                    Label eventFees = (Label) bottomHBox.getChildren().get(0);

                    Address address = Database.events.get(i).getRoom().getAddress();

                    eventLoc.setText(eventLoc.getText() + " " + address.country + ", " + address.city + ", " + address.street + ".");
                    eventOrganizer.setText(eventOrganizer.getText() + " " +  Database.events.get(i).getOrganizer().getUserName() + ".");
                    String outdoors = "outdoors.";
                    if (!Database.events.get(i).getOutdoors()) outdoors = "indoors.";
                    eventOutdoors.setText(eventOutdoors.getText() +  outdoors);
                    eventFees.setText(eventFees.getText() + " " +  Database.events.get(i).getFees() + ".");

                    rootV.getChildren().add(newVBox);
                }
            }
        }else {
            System.out.println("I am working");
            displayEvents();
        }
    }

    @FXML
    private void handleTheaterCategory(){
        VBox rootV = (VBox) scrollpane.getContent();
        boolean toggle = toggleBackground(theaterCategory);

        if (toggle){

            rootV.getChildren().clear();

            for (int i =0; i < Database.events.size(); i++){

                if(Database.events.get(i).getCategory().getType().equals(CategoryType.THEATER)){
                    VBox newVBox;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vbox_layout.fxml"));
        newVBox = loader.load();
        VBoxController vBoxController = loader.getController();
        vBoxController.setDisableButton(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Node hbox1 = newVBox.getChildren().get(0);
                    HBox hbox = (HBox) hbox1;
                    Label eventName = (Label) hbox.getChildren().get(0);
                    Label eventDate = (Label) hbox.getChildren().get(1);

                    eventName.setText(Database.events.get(i).getEventName());
                    Date date = Database.events.get(i).getEventDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
                    String dateString = formatter.format(date);
                    eventDate.setText(dateString);

                    VBox innerV = (VBox) newVBox.getChildren().get(1);
                    Label eventLoc = (Label) innerV.getChildren().get(0);
                    Label eventOrganizer = (Label) innerV.getChildren().get(1);
                    Label eventOutdoors = (Label) innerV.getChildren().get(2);
                    Label eventActivities = (Label) innerV.getChildren().get(3);
                        eventActivities.setText("-" + Database.events.get(i).getCategory().getName() + " Event");
                    HBox bottomHBox = (HBox) innerV.getChildren().get(4);
                    Label eventFees = (Label) bottomHBox.getChildren().get(0);

                    Address address = Database.events.get(i).getRoom().getAddress();

                    eventLoc.setText(eventLoc.getText() + " " + address.country + ", " + address.city + ", " + address.street + ".");
                    eventOrganizer.setText(eventOrganizer.getText() + " " +  Database.events.get(i).getOrganizer().getUserName() + ".");
                    String outdoors = "outdoors.";
                    if (!Database.events.get(i).getOutdoors()) outdoors = "indoors.";
                    eventOutdoors.setText(eventOutdoors.getText() +  outdoors);
                    eventFees.setText(eventFees.getText() + " " +  Database.events.get(i).getFees() + ".");

                    rootV.getChildren().add(newVBox);
                }
            }
        }else {
            System.out.println("I am working");
            displayEvents();
        }
    }

    @FXML
    private void handleConferenceCategory(){
        VBox rootV = (VBox) scrollpane.getContent();
        boolean toggle = toggleBackground(conferenceCategory);

        if (toggle){

            rootV.getChildren().clear();

            for (int i =0; i < Database.events.size(); i++){

                if(Database.events.get(i).getCategory().getType().equals(CategoryType.CONFERENCE)){
                    VBox newVBox;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vbox_layout.fxml"));
        newVBox = loader.load();
        VBoxController vBoxController = loader.getController();
        vBoxController.setDisableButton(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Node hbox1 = newVBox.getChildren().get(0);
                    HBox hbox = (HBox) hbox1;
                    Label eventName = (Label) hbox.getChildren().get(0);
                    Label eventDate = (Label) hbox.getChildren().get(1);

                    eventName.setText(Database.events.get(i).getEventName());
                    Date date = Database.events.get(i).getEventDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
                    String dateString = formatter.format(date);
                    eventDate.setText(dateString);

                    VBox innerV = (VBox) newVBox.getChildren().get(1);
                    Label eventLoc = (Label) innerV.getChildren().get(0);
                    Label eventOrganizer = (Label) innerV.getChildren().get(1);
                    Label eventOutdoors = (Label) innerV.getChildren().get(2);
                    Label eventActivities = (Label) innerV.getChildren().get(3);
                        eventActivities.setText("-" + Database.events.get(i).getCategory().getName() + " Event");
                    HBox bottomHBox = (HBox) innerV.getChildren().get(4);
                    Label eventFees = (Label) bottomHBox.getChildren().get(0);

                    Address address = Database.events.get(i).getRoom().getAddress();

                    eventLoc.setText(eventLoc.getText() + " " + address.country + ", " + address.city + ", " + address.street + ".");
                    eventOrganizer.setText(eventOrganizer.getText() + " " +  Database.events.get(i).getOrganizer().getUserName() + ".");
                    String outdoors = "outdoors.";
                    if (!Database.events.get(i).getOutdoors()) outdoors = "indoors.";
                    eventOutdoors.setText(eventOutdoors.getText() +  outdoors);
                    eventFees.setText(eventFees.getText() + " " +  Database.events.get(i).getFees() + ".");

                    rootV.getChildren().add(newVBox);
                }
            }
        }else {
            System.out.println("I am working");
            displayEvents();
        }
    }

    @FXML
    private void handleOtherCategory(){
        VBox rootV = (VBox) scrollpane.getContent();
        boolean toggle = toggleBackground(otherCategory);

        if (toggle){

            rootV.getChildren().clear();

            for (int i =0; i < Database.events.size(); i++){

                if(Database.events.get(i).getCategory().getType().equals(CategoryType.OTHER)){
                    VBox newVBox;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/vbox_layout.fxml"));
        newVBox = loader.load();
        VBoxController vBoxController = loader.getController();
        vBoxController.setDisableButton(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Node hbox1 = newVBox.getChildren().get(0);
                    HBox hbox = (HBox) hbox1;
                    Label eventName = (Label) hbox.getChildren().get(0);
                    Label eventDate = (Label) hbox.getChildren().get(1);

                    eventName.setText(Database.events.get(i).getEventName());
                    Date date = Database.events.get(i).getEventDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
                    String dateString = formatter.format(date);
                    eventDate.setText(dateString);

                    VBox innerV = (VBox) newVBox.getChildren().get(1);
                    Label eventLoc = (Label) innerV.getChildren().get(0);
                    Label eventOrganizer = (Label) innerV.getChildren().get(1);
                    Label eventOutdoors = (Label) innerV.getChildren().get(2);
                    Label eventActivities = (Label) innerV.getChildren().get(3);
                        eventActivities.setText("-" + Database.events.get(i).getCategory().getName() + " Event");
                    HBox bottomHBox = (HBox) innerV.getChildren().get(4);
                    Label eventFees = (Label) bottomHBox.getChildren().get(0);

                    Address address = Database.events.get(i).getRoom().getAddress();

                    eventLoc.setText(eventLoc.getText() + " " + address.country + ", " + address.city + ", " + address.street + ".");
                    eventOrganizer.setText(eventOrganizer.getText() + " " +  Database.events.get(i).getOrganizer().getUserName() + ".");
                    String outdoors = "outdoors.";
                    if (!Database.events.get(i).getOutdoors()) outdoors = "indoors.";
                    eventOutdoors.setText(eventOutdoors.getText() +  outdoors);
                    eventFees.setText(eventFees.getText() + " " +  Database.events.get(i).getFees() + ".");

                    rootV.getChildren().add(newVBox);
                }
            }
        }else {
            displayEvents();
        }
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
    public static void handleBuy(Label eventName){



        Event event = new Event();
        for (int i = 0; i < Database.events.size(); i++){
            if (Database.events.get(i).getEventName().equals(eventName)){
                event = Database.events.get(i);
            }
        }

      
    }
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/adminLanding.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

}
