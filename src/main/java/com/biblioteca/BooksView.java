package com.biblioteca;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class BooksView {
    private App app;

    public BooksView(App app) {
        this.app = app;
    }

    public BooksView() {
    }

    public Scene getScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/books-view.fxml"));
            Parent root = loader.load();
            loader.<BooksView>getController().app = app;
            return new Scene(root, 800, 600);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar la vista de libros.", exception);
        }
    }

    public void goBack() {
        app.showMainView();
    }
}
