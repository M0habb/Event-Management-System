package GUI;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class ManageCategoriesController {
    @FXML private TableView<Category> manageCategories;
    @FXML private TableColumn<Category, Number> idColumn;
    @FXML private TableColumn<Category, String> typeColumn;
    @FXML private TableColumn<Category, Void> updateColumn;
    @FXML private TableColumn<Category, Void> deleteColumn;

    private ObservableList<Category> categoryList;

    @FXML
    public void initialize() {
        categoryList = FXCollections.observableArrayList(Database.categories);
        manageCategories.setItems(categoryList);

        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getID()));
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
                    getTableView().edit(getIndex(), typeColumn);
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
                    new Admin().delete(category.getID());
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
}
