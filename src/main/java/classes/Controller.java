package classes;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;

public class Controller extends Application{

    public static void main(String[] args) {

        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/main.fxml"));
        primaryStage.setTitle("JavaFX App");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
