package com.biblioteca;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersView {
    private App app;

    public UsersView(App app) {
        this.app = app;
    }

    public Scene getScene() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("Gestión de Usuarios");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField txtNombre = new TextField();
        TextField txtApellidos = new TextField();
        TextField txtCorreo = new TextField();
        TextField txtTelefono = new TextField();
        TextField txtDireccion = new TextField();
        DatePicker dpFechaRegistro = new DatePicker();
        TextField txtEstado = new TextField();
        Label lblMensaje = new Label();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(new Label("Apellidos:"), 0, 1);
        grid.add(txtApellidos, 1, 1);
        grid.add(new Label("Correo electrónico:"), 0, 2);
        grid.add(txtCorreo, 1, 2);
        grid.add(new Label("Teléfono:"), 0, 3);
        grid.add(txtTelefono, 1, 3);
        grid.add(new Label("Dirección:"), 0, 4);
        grid.add(txtDireccion, 1, 4);
        grid.add(new Label("Fecha de registro:"), 0, 5);
        grid.add(dpFechaRegistro, 1, 5);
        grid.add(new Label("Estado:"), 0, 6);
        grid.add(txtEstado, 1, 6);
        grid.add(lblMensaje, 1, 7);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            if (camposObligatoriosIncompletos(txtNombre, txtApellidos, txtCorreo, txtTelefono, txtDireccion, dpFechaRegistro, txtEstado)) {
                lblMensaje.setText("Error: completa todos los campos obligatorios.");
                lblMensaje.setStyle("-fx-text-fill: #c62828;");
                return;
            }

            String sql = "INSERT INTO usuarios (nombre, apellidos, correo, telefono, direccion, fecha_registro, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, txtNombre.getText().trim());
                statement.setString(2, txtApellidos.getText().trim());
                statement.setString(3, txtCorreo.getText().trim());
                statement.setString(4, txtTelefono.getText().trim());
                statement.setString(5, txtDireccion.getText().trim());
                statement.setDate(6, Date.valueOf(dpFechaRegistro.getValue()));
                statement.setString(7, txtEstado.getText().trim());
                statement.executeUpdate();

                lblMensaje.setText("Usuario guardado correctamente.");
                lblMensaje.setStyle("-fx-text-fill: #2e7d32;");
                limpiarCampos(txtNombre, txtApellidos, txtCorreo, txtTelefono, txtDireccion, txtEstado);
                dpFechaRegistro.setValue(null);
            } catch (SQLException ex) {
                lblMensaje.setText("Error al guardar en la base de datos: " + ex.getMessage());
                lblMensaje.setStyle("-fx-text-fill: #c62828;");
            }
        });

        Button btnLimpiar = new Button("Limpiar");
        btnLimpiar.setOnAction(e -> {
            limpiarCampos(txtNombre, txtApellidos, txtCorreo, txtTelefono, txtDireccion, txtEstado);
            dpFechaRegistro.setValue(null);
            lblMensaje.setText("");
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(btnGuardar, btnLimpiar);

        Button btnBack = new Button("Volver al Inicio");
        btnBack.setOnAction(e -> app.showMainView());

        root.getChildren().addAll(title, grid, buttonBox, btnBack);
        return new Scene(root, 700, 450);
    }

    private void limpiarCampos(TextField... fields) {
        for (TextField f : fields) {
            f.clear();
        }
    }

    private boolean camposObligatoriosIncompletos(TextField txtNombre, TextField txtApellidos, TextField txtCorreo,
                                                   TextField txtTelefono, TextField txtDireccion,
                                                   DatePicker dpFechaRegistro, TextField txtEstado) {
        return txtNombre.getText().trim().isEmpty()
                || txtApellidos.getText().trim().isEmpty()
                || txtCorreo.getText().trim().isEmpty()
                || txtTelefono.getText().trim().isEmpty()
                || txtDireccion.getText().trim().isEmpty()
                || dpFechaRegistro.getValue() == null
                || txtEstado.getText().trim().isEmpty();
    }
}