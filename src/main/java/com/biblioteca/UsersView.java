package com.biblioteca;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UsersView {
    private App app;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private DatePicker dpFechaRegistro;
    @FXML private TextField txtEstado;
    @FXML private Label lblMensaje;

    public UsersView(App app) {
        this.app = app;
    }

    public UsersView() {
    }

    public Scene getScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/users-view.fxml"));
            Parent root = loader.load();
            UsersView controller = loader.getController();
            controller.app = app;
            return new Scene(root, 700, 450);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar la vista de usuarios.", exception);
        }
    }

    @FXML
    private void saveUser() {
        if (camposObligatoriosIncompletos()) {
            showError("Error: completa todos los campos obligatorios.");
            return;
        }

        String sql = "INSERT INTO usuarios (nombre, apellidos, correo, telefono, direccion, fecha_registro, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, txtNombre.getText().trim());
            statement.setString(2, txtApellidos.getText().trim());
            statement.setString(3, txtCorreo.getText().trim());
            statement.setString(4, txtTelefono.getText().trim());
            statement.setString(5, txtDireccion.getText().trim());
            statement.setDate(6, Date.valueOf(dpFechaRegistro.getValue()));
            statement.setString(7, txtEstado.getText().trim());
            statement.executeUpdate();
            lblMensaje.setStyle("-fx-text-fill: #2e7d32;");
            lblMensaje.setText("Usuario guardado correctamente.");
            clearFields();
        } catch (SQLException exception) {
            showError("Error al guardar en la base de datos: " + exception.getMessage());
        }
    }

    @FXML
    private void clearFields() {
        txtNombre.clear();
        txtApellidos.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtEstado.clear();
        dpFechaRegistro.setValue(null);
        lblMensaje.setText("");
    }

    @FXML
    private void goBack() {
        app.showMainView();
    }

    private boolean camposObligatoriosIncompletos() {
        return txtNombre.getText().trim().isEmpty()
                || txtApellidos.getText().trim().isEmpty()
                || txtCorreo.getText().trim().isEmpty()
                || txtTelefono.getText().trim().isEmpty()
                || txtDireccion.getText().trim().isEmpty()
                || dpFechaRegistro.getValue() == null
                || txtEstado.getText().trim().isEmpty();
    }

    private void showError(String message) {
        lblMensaje.setStyle("-fx-text-fill: #c62828;");
        lblMensaje.setText(message);
    }
}
