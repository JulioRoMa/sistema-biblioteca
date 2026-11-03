package com.biblioteca;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CategoriesView {
    private App app;

    public CategoriesView(App app) {
        this.app = app;
    }

    public CategoriesView() {
    }

    public Scene getScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/categories-view.fxml"));
            Parent root = loader.load();
            loader.<CategoriesView>getController().app = app;
            return new Scene(root, 800, 600);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar la vista de categorías.", exception);
        }
    }

    public void goBack() {
        app.showMainView();
    }
}
