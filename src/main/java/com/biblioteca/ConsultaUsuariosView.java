package com.biblioteca;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConsultaUsuariosView {
    private final App app;
    private final UserDAO userDAO = new UserDAO();
    private final TableView<User> table = new TableView<>();
    private final TextField searchField = new TextField();
    private final ComboBox<String> statusBox = new ComboBox<>();
    private final Label countLabel = new Label("Registros encontrados: 0");
    private final Label messageLabel = new Label();

    public ConsultaUsuariosView(App app) {
        this.app = app;
        configureTable();
        configureStatusFilter();
        loadUsers();
    }

    public Scene getScene() {
        Label title = new Label("Consulta de usuarios");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        searchField.setPromptText("Nombre o apellido");
        searchField.setPrefWidth(240);
        statusBox.setPrefWidth(150);

        Button searchButton = new Button("Buscar");
        searchButton.setOnAction(event -> loadUsers());

        Button showAllButton = new Button("Mostrar todos");
        showAllButton.setOnAction(event -> {
            searchField.clear();
            statusBox.setValue("Todos");
            loadUsers();
        });

        Button clearButton = new Button("Limpiar filtros");
        clearButton.setOnAction(event -> {
            searchField.clear();
            statusBox.setValue("Todos");
            messageLabel.setText("");
            loadUsers();
        });

        Button backButton = new Button("Volver al inicio");
        backButton.setOnAction(event -> app.showMainView());

        HBox filters = new HBox(10,
                new Label("Buscar:"), searchField,
                new Label("Estado:"), statusBox,
                searchButton, showAllButton, clearButton);
        HBox footer = new HBox(20, countLabel, messageLabel, backButton);

        VBox top = new VBox(12, title, filters);
        top.setPadding(new Insets(20, 20, 10, 20));
        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(table);
        root.setBottom(footer);
        BorderPane.setMargin(table, new Insets(0, 20, 10, 20));
        BorderPane.setMargin(footer, new Insets(0, 20, 20, 20));
        return new Scene(root, 1200, 620);
    }

    private void configureTable() {
        addColumn("ID", "id", 60);
        addColumn("Nombre", "nombre", 120);
        addColumn("Apellidos", "apellidos", 150);
        addColumn("Correo", "email", 190);
        addColumn("Teléfono", "telefono", 110);
        addColumn("Dirección", "direccion", 220);
        addColumn("Fecha de registro", "fechaRegistro", 130);
        addColumn("Estado", "estado", 100);
        table.setPlaceholder(new Label("No hay registros para mostrar."));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void addColumn(String title, String property, double width) {
        TableColumn<User, String> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setPrefWidth(width);
        table.getColumns().add(column);
    }

    private void configureStatusFilter() {
        try {
            ObservableList<String> statuses = FXCollections.observableArrayList("Todos");
            statuses.addAll(userDAO.findStatuses());
            statusBox.setItems(statuses);
            statusBox.setValue("Todos");
        } catch (Exception exception) {
            statusBox.setItems(FXCollections.observableArrayList("Todos", "Activo", "Inactivo"));
            statusBox.setValue("Todos");
            showError("No se pudieron cargar los estados: " + exception.getMessage());
        }
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
