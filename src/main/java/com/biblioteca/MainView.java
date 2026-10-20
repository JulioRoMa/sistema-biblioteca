package com.biblioteca;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainView {
    private App app;

    public MainView(App app) {
        this.app = app;
    }

    public Scene getScene() {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label title = new Label("SISTEMA DE BIBLIOTECA");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        

        Button btnUsers = new Button("Módulo de Usuarios");
        btnUsers.setMaxWidth(200);
        btnUsers.setOnAction(e -> app.showView(new UsersView(app).getScene()));

        Button btnBooks = new Button("Módulo de Libros");
        btnBooks.setMaxWidth(200);
        btnBooks.setOnAction(e -> app.showView(new BooksView(app).getScene()));

        Button btnAuthors = new Button("Módulo de Autores");
        btnAuthors.setMaxWidth(200);
        btnAuthors.setOnAction(e -> app.showView(new AuthorsView(app).getScene()));

        Button btnCategories = new Button("Módulo de Categorías");
        btnCategories.setMaxWidth(200);
        btnCategories.setOnAction(e -> app.showView(new CategoriesView(app).getScene()));

        Button btnLoans = new Button("Módulo de Préstamos");
        btnLoans.setMaxWidth(200);
        btnLoans.setOnAction(e -> app.showView(new LoansView(app).getScene()));

        Button btnReturns = new Button("Módulo de Devoluciones");
        btnReturns.setMaxWidth(200);
        btnReturns.setOnAction(e -> app.showView(new ReturnsView(app).getScene()));

        Button btnExit = new Button("Salir del Sistema");
        btnExit.setMaxWidth(200);
        btnExit.setOnAction(e -> System.exit(0));

        root.getChildren().addAll(title, btnUsers, btnBooks, btnAuthors, btnCategories, btnLoans, btnReturns, btnExit);

        return new Scene(root, 600, 600);
    }
}
