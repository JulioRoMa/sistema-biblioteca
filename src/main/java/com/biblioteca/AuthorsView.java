package com.biblioteca;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AuthorsView {
    private App app;

    public AuthorsView(App app) {
        this.app = app;
    }

    public AuthorsView() {
    }

    public Scene getScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/authors-view.fxml"));
            Parent root = loader.load();
            loader.<AuthorsView>getController().app = app;
            return new Scene(root, 800, 600);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar la vista de autores.", exception);
        }
    }

    public void goBack() {
        app.showMainView();
    }
}
