package com.biblioteca;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CategoriesView {
    private App app;

    public CategoriesView(App app) {
        this.app = app;
    }

    public Scene getScene() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("Gestión de Categorías");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("ID:"), 0, 0);
        grid.add(new TextField(), 1, 0);
        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(new TextField(), 1, 1);
        grid.add(new Label("Descripción:"), 0, 2);
        grid.add(new TextField(), 1, 2);

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
