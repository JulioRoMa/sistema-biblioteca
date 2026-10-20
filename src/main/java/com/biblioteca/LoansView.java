package com.biblioteca;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LoansView {
    private App app;

    public LoansView(App app) {
        this.app = app;
    }

    public Scene getScene() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("Registro de Préstamos");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Número de préstamo:"), 0, 0);
        grid.add(new TextField(), 1, 0);
        grid.add(new Label("Usuario:"), 0, 1);
        grid.add(new TextField(), 1, 1);
        grid.add(new Label("Libro o ejemplar:"), 0, 2);
        grid.add(new TextField(), 1, 2);
        grid.add(new Label("Fecha de préstamo:"), 0, 3);
        grid.add(new TextField(), 1, 3);
        grid.add(new Label("Fecha límite de devolución:"), 0, 4);
        grid.add(new TextField(), 1, 4);
        grid.add(new Label("Bibliotecario:"), 0, 5);
        grid.add(new TextField(), 1, 5);
        grid.add(new Label("Estado:"), 0, 6);
        grid.add(new TextField(), 1, 6);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(new Button("Registrar Préstamo"), new Button("Buscar"), new Button("Limpiar"));

        TableView table = new TableView();
        table.setPlaceholder(new Label("No hay datos registrados"));

        Button btnBack = new Button("Volver al Inicio");
        btnBack.setOnAction(e -> app.showMainView());

        root.getChildren().addAll(title, grid, buttonBox, table, btnBack);
        return new Scene(root, 800, 600);
    }
}
