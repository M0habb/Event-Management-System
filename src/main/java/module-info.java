module Event.Management.System {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens classes to javafx.fxml;
    exports classes;
    exports GUI;
    opens GUI to javafx.fxml;
}