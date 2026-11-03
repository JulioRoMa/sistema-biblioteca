package com.biblioteca;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainView {
    private App app;

    public MainView(App app) {
        this.app = app;
    }

    public MainView() {
    }

    public Scene getScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main-view.fxml"));
            Parent root = loader.load();
            loader.<MainView>getController().setApp(app);
            return new Scene(root, 600, 600);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar la vista principal.", exception);
        }
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void openUsers() {
        app.showView(new UsersView(app).getScene());
    }

    public void openUserQueries() {
        app.showView(new ConsultaUsuariosView(app).getScene());
    }

    public void openBooks() {
        app.showView(new BooksView(app).getScene());
    }

    public void openAuthors() {
        app.showView(new AuthorsView(app).getScene());
    }

    public void openCategories() {
        app.showView(new CategoriesView(app).getScene());
    }

    public void openLoans() {
        app.showView(new LoansView(app).getScene());
    }

    public void openReturns() {
        app.showView(new ReturnsView(app).getScene());
    }

    public void exit() {
        System.exit(0);
    }
}
