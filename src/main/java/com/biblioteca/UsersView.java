package com.biblioteca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class UsersView {
    private App app;
    private ObservableList<User> userList = FXCollections.observableArrayList();

    public UsersView(App app) {
        this.app = app;
    }

    public Scene getScene() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("Gestión de Usuarios");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Todos los campos de texto
        TextField txtId = new TextField();
        TextField txtNombre = new TextField();
        TextField txtApellidos = new TextField();
        TextField txtEmail = new TextField();
        TextField txtTelefono = new TextField();
        TextField txtDireccion = new TextField();
        TextField txtFechaRegistro = new TextField();
        TextField txtEstado = new TextField();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("ID del usuario:"), 0, 0);
        grid.add(txtId, 1, 0);
        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(txtNombre, 1, 1);
        grid.add(new Label("Apellidos:"), 0, 2);
        grid.add(txtApellidos, 1, 2);
        grid.add(new Label("Correo electrónico:"), 0, 3);
        grid.add(txtEmail, 1, 3);
        grid.add(new Label("Teléfono:"), 0, 4);
        grid.add(txtTelefono, 1, 4);
        grid.add(new Label("Dirección:"), 0, 5);
        grid.add(txtDireccion, 1, 5);
        grid.add(new Label("Fecha de registro:"), 0, 6);
        grid.add(txtFechaRegistro, 1, 6);
        grid.add(new Label("Estado:"), 0, 7);
        grid.add(txtEstado, 1, 7);

        // Configuración de TableView con las 8 columnas
        TableView<User> table = new TableView<>();
        
        TableColumn<User, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<User, String> colApellidos = new TableColumn<>("Apellidos");
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        TableColumn<User, String> colEmail = new TableColumn<>("Correo");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, String> colTelefono = new TableColumn<>("Teléfono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<User, String> colDireccion = new TableColumn<>("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<User, String> colFecha = new TableColumn<>("Fecha Reg.");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

        TableColumn<User, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        table.getColumns().addAll(colId, colNombre, colApellidos, colEmail, colTelefono, colDireccion, colFecha, colEstado);
        table.setItems(userList);

        // Funcionalidad de los botones
        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            if (!txtId.getText().isEmpty() && !txtNombre.getText().isEmpty()) {
                userList.add(new User(
                    txtId.getText(),
                    txtNombre.getText(),
                    txtApellidos.getText(),
                    txtEmail.getText(),
                    txtTelefono.getText(),
                    txtDireccion.getText(),
                    txtFechaRegistro.getText(),
                    txtEstado.getText()
                ));
                limpiarCampos(txtId, txtNombre, txtApellidos, txtEmail, txtTelefono, txtDireccion, txtFechaRegistro, txtEstado);
            }
        });

        Button btnLimpiar = new Button("Limpiar");
        btnLimpiar.setOnAction(e -> limpiarCampos(txtId, txtNombre, txtApellidos, txtEmail, txtTelefono, txtDireccion, txtFechaRegistro, txtEstado));

        Button btnNuevo = new Button("Nuevo");
        Button btnBuscar = new Button("Buscar");
        Button btnModificar = new Button("Modificar");
        Button btnEliminar = new Button("Eliminar");

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(btnNuevo, btnGuardar, btnBuscar, btnModificar, btnEliminar, btnLimpiar);

        Button btnBack = new Button("Volver al Inicio");
        btnBack.setOnAction(e -> app.showMainView());

        root.getChildren().addAll(title, grid, buttonBox, table, btnBack);
        return new Scene(root, 900, 700);
    }

    private void limpiarCampos(TextField... fields) {
        for (TextField f : fields) {
            f.clear();
        }
    }
}