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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageCategoriesController {

    @FXML private TableView<Category> manageCategories;
    @FXML private TableColumn<Category, String> idColumn;
    @FXML private TableColumn<Category, CategoryType> typeColumn;
    @FXML private TableColumn<Category, Void> updateColumn;
    @FXML private TableColumn<Category, Void> deleteColumn;

    @FXML private Button musicCategory;
    @FXML private Button sportsCategory;
    @FXML private Button theaterCategory;
    @FXML private Button conferenceCategory;
    @FXML private Button otherCategory;

    private ObservableList<Category> categoryList = FXCollections.observableArrayList(Database.categories);

    private List<Button> categoryButtons;
    private Map<Button, String> categoryButtonColors;

    @FXML private Text usernameLabel;
    Admin currentUser = (Admin) User.currentUser;

    @FXML
    private TextField nameTextField;
    @FXML
    private Label required;

    @FXML
    public void initialize() {

        required.setVisible(false);

        usernameLabel.setText(currentUser.getUserName());

        idColumn.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
        idColumn.setCellFactory(col -> new EditingCell());

        typeColumn.setCellValueFactory(cellData -> cellData.getValue().categoryTypeProperty());
        typeColumn.setCellFactory(col -> new EditableComboBoxCell());

        // Disable editing until Update is clicked
        idColumn.setEditable(true);
        typeColumn.setEditable(true);
        manageCategories.setEditable(true);

        addUpdateButtons();
        addDeleteButtons();

        manageCategories.setItems(categoryList);

        categoryButtons = Arrays.asList(musicCategory, sportsCategory, theaterCategory, conferenceCategory, otherCategory);

        // Assign each button its unique color
        categoryButtonColors = new HashMap<>();
        categoryButtonColors.put(musicCategory, "#f1c40f");
        categoryButtonColors.put(sportsCategory, "#16a085");
        categoryButtonColors.put(theaterCategory, "#3498db");
        categoryButtonColors.put(conferenceCategory, "#9b59b6");
        categoryButtonColors.put(otherCategory, "#95a5a6");

        categoryButtons.forEach(categoryButton -> categoryButton.setOnAction(e -> toggleBackground(categoryButton) ) );
    }

    private void addUpdateButtons() {
        updateColumn.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button("Update");

            {
                button.setOnAction(e -> {
                    int rowIndex = getIndex();
                    Category cat = getTableView().getItems().get(rowIndex);

                    if (!cat.isEditable()) {
                        cat.setEditable(true);
                        button.setText("Confirm");

                        Database.categories.get(rowIndex).setCategoryName(cat.getName());
                        Database.categories.get(rowIndex).setCategoryType(cat.getType());

                    } else {
                        cat.setEditable(false);
                        button.setText("Update");
                        manageCategories.refresh();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Category cat = getTableView().getItems().get(getIndex());
                    button.setText(cat.isEditable() ? "Confirm" : "Update");
                    setGraphic(button);
                }
            }
        });
    }

    private void addDeleteButtons() {
        deleteColumn.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button("Delete");

            {
                button.setOnAction(e -> {
                    Category cat = getTableView().getItems().get(getIndex());
                    categoryList.remove(cat);
                    for (int i = 0; i < Database.categories.size(); i++){
                        if (cat == Database.categories.get(i)){
                            Database.categories.remove(i);
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : button);
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

    @FXML
    private void handleCreate(){

        String type = "";
        for (int i = 0; i < categoryButtons.size(); i++){
            if(categoryButtons.get(i).getStyle().contains(categoryButtonColors.get(categoryButtons.get(i)))){
                type = categoryButtons.get(i).getText();
            }
        }

        if (nameTextField.getText().isEmpty() || type.isEmpty()){
            required.setVisible(true);
            return;
        }

        type = type.replaceAll("\\s+$", "");

        Category category = new Category(nameTextField.getText(), CategoryType.valueOf(type.toUpperCase()));

        categoryList = FXCollections.observableArrayList(Database.categories);
        manageCategories.setItems(categoryList);
        manageCategories.refresh();

        required.setVisible(false);

        nameTextField.setText("");
        for (int i = 0; i < categoryButtons.size(); i++){
            if(categoryButtons.get(i).getStyle().contains(categoryButtonColors.get(categoryButtons.get(i)))){
                categoryButtons.get(i).setStyle("-fx-background-color: transparent;");
            }
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

}

class EditingCell extends TableCell<Category, String> {
    private final TextField textField = new TextField();

    public EditingCell() {
        textField.setOnAction(e -> commitEdit(textField.getText()));
        textField.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case ENTER -> commitEdit(textField.getText());
                case ESCAPE -> cancelEdit();
            }
        });
    }

    @Override
    public void startEdit() {
        Category cat = getTableRow().getItem();
        if (cat != null && cat.isEditable()) {
            super.startEdit();
            textField.setText(getItem());
            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.requestFocus();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void commitEdit(String newValue) {
        super.commitEdit(newValue);
        getTableView().getItems().get(getIndex()).setCategoryName(newValue);
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || getTableRow() == null || getTableRow().getItem() == null) {
            setText(null);
            setGraphic(null);
        } else {
            Category cat = getTableRow().getItem();
            if (isEditing()) {
                textField.setText(item);
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(item);
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
            setEditable(cat.isEditable()); // 👈 Respect editable flag
        }
    }
}

class EditableComboBoxCell extends TableCell<Category, CategoryType> {
    private final ComboBox<CategoryType> comboBox = new ComboBox<>();

    public EditableComboBoxCell() {
        comboBox.getItems().setAll(CategoryType.values());
        comboBox.setOnAction(e -> {
            Category cat = getTableRow().getItem();
            if (cat != null) {
                cat.setCategoryType(comboBox.getValue());
            }
        });
    }

    @Override
    protected void updateItem(CategoryType item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || getTableRow() == null || getTableRow().getItem() == null) {
            setText(null);
            setGraphic(null);
        } else {
            Category cat = getTableRow().getItem();
            if (cat.isEditable()) {
                comboBox.setValue(item);
                setGraphic(comboBox);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(item.toString());
                setGraphic(null);
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }
}


