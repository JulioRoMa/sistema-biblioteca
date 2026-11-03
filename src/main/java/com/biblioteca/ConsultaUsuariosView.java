package com.biblioteca;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsultaUsuariosView {
    private App app;
    private final UserDAO userDAO = new UserDAO();
    @FXML private TableView<User> table;
    @FXML private TableColumn<User, String> idColumn;
    @FXML private TableColumn<User, String> nombreColumn;
    @FXML private TableColumn<User, String> apellidosColumn;
    @FXML private TableColumn<User, String> emailColumn;
    @FXML private TableColumn<User, String> telefonoColumn;
    @FXML private TableColumn<User, String> direccionColumn;
    @FXML private TableColumn<User, String> fechaColumn;
    @FXML private TableColumn<User, String> estadoColumn;
    @FXML private TextField searchField;
    @FXML private ComboBox<String> statusBox;
    @FXML private Label countLabel;
    @FXML private Label messageLabel;

    public ConsultaUsuariosView(App app) {
        this.app = app;
    }

    public ConsultaUsuariosView() {
    }

    public Scene getScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/consulta-usuarios-view.fxml"));
            Parent root = loader.load();
            ConsultaUsuariosView controller = loader.getController();
            controller.app = app;
            controller.initializeData();
            return new Scene(root, 1200, 620);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar la vista de consulta.", exception);
        }
    }

    private void initializeData() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        try {
            statusBox.setItems(FXCollections.observableArrayList("Todos"));
            statusBox.getItems().addAll(userDAO.findStatuses());
            statusBox.setValue("Todos");
            loadUsers();
        } catch (Exception exception) {
            statusBox.setItems(FXCollections.observableArrayList("Todos", "Activo", "Inactivo"));
            statusBox.setValue("Todos");
            showError("No se pudieron cargar los estados: " + exception.getMessage());
        }
    }

    @FXML
    private void searchUsers() {
        loadUsers();
    }

    @FXML
    private void showAllUsers() {
        searchField.clear();
        statusBox.setValue("Todos");
        loadUsers();
    }

    @FXML
    private void clearFilters() {
        searchField.clear();
        statusBox.setValue("Todos");
        messageLabel.setText("");
        loadUsers();
    }

    @FXML
    private void goBack() {
        app.showMainView();
    }

    private void loadUsers() {
        try {
            List<User> users = userDAO.find(searchField.getText(), statusBox.getValue());
            table.setItems(FXCollections.observableArrayList(users));
            countLabel.setText("Registros encontrados: " + users.size());
            if (users.isEmpty()) {
                showInfo("No se encontraron coincidencias.");
            } else {
                messageLabel.setText("");
            }
        } catch (Exception exception) {
            table.setItems(FXCollections.observableArrayList());
            countLabel.setText("Registros encontrados: 0");
            showError("Error al consultar usuarios: " + exception.getMessage());
        }
    }

    private void showError(String message) {
        messageLabel.setStyle("-fx-text-fill: #c62828;");
        messageLabel.setText(message);
    }

    private void showInfo(String message) {
        messageLabel.setStyle("-fx-text-fill: #555555;");
        messageLabel.setText(message);
    }
}
