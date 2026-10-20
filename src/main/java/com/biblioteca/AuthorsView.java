package com.biblioteca;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AuthorsView {
    private App app;

    public AuthorsView(App app) {
        this.app = app;
    }

    public Scene getScene() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("Gestión de Autores");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("ID del autor:"), 0, 0);
        grid.add(new TextField(), 1, 0);
        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(new TextField(), 1, 1);
        grid.add(new Label("Apellidos:"), 0, 2);
        grid.add(new TextField(), 1, 2);
        grid.add(new Label("Nacionalidad:"), 0, 3);
        grid.add(new TextField(), 1, 3);
        grid.add(new Label("Fecha de nacimiento:"), 0, 4);
        grid.add(new TextField(), 1, 4);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(new Button("Nuevo"), new Button("Guardar"), new Button("Buscar"), new Button("Modificar"), new Button("Eliminar"), new Button("Limpiar"));

        TableView table = new TableView();
        table.setPlaceholder(new Label("No hay datos registrados"));

        Button btnBack = new Button("Volver al Inicio");
        btnBack.setOnAction(e -> app.showMainView());

        root.getChildren().addAll(title, grid, buttonBox, table, btnBack);
        return new Scene(root, 800, 600);
    }
}
