package com.biblioteca;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ReturnsView {
    private App app;

    public ReturnsView(App app) {
        this.app = app;
    }

    public ReturnsView() {
    }

    public Scene getScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/returns-view.fxml"));
            Parent root = loader.load();
            loader.<ReturnsView>getController().app = app;
            return new Scene(root, 800, 600);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar la vista de devoluciones.", exception);
        }
    }

    public void goBack() {
        app.showMainView();
    }
}
