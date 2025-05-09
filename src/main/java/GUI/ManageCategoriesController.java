package GUI;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

import java.io.IOException;

public class ManageCategoriesController {
    @FXML private TableView<Category> manageCategories;
    @FXML private TableColumn<Category, String> idColumn;
    @FXML private TableColumn<Category, String> typeColumn;
    @FXML private TableColumn<Category, Void> updateColumn;
    @FXML private TableColumn<Category, Void> deleteColumn;

    @FXML private Text usernameLabel;

    Admin currentUser = (Admin) User.currentUser;

    private ObservableList<Category> categoryList;

    @FXML
    public void initialize() {

        usernameLabel.setText(currentUser.getUserName());
        displayTable();

    }

    private void displayTable(){

        manageCategories.setEditable(true);
        idColumn.setEditable(true);
        typeColumn.setEditable(true);

        categoryList = FXCollections.observableArrayList(Database.categories);
        manageCategories.setItems(categoryList);

        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        typeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getType().toString()));

        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setOnEditCommit(event -> {
            Category category = event.getRowValue();
            category.setType(CategoryType.valueOf(event.getNewValue()));
            new Admin().update(category);
        });

        updateColumn.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Update");

            {
                btn.setOnAction(e -> {
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

        deleteColumn.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Delete");

            {
                btn.setOnAction(e -> {
                    Category category = getTableView().getItems().get(getIndex());
                    new Admin().delete(category.getName());
                    categoryList.remove(category);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/adminLanding.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void handleSignout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));

        Scene scene = new Scene(root, 1142, 642);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

}
