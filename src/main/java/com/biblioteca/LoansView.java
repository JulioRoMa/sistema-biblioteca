package com.biblioteca;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoansView {
    private App app;

    public LoansView(App app) {
        this.app = app;
    }

    public LoansView() {
    }

    public Scene getScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loans-view.fxml"));
            Parent root = loader.load();
            loader.<LoansView>getController().app = app;
            return new Scene(root, 800, 600);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar la vista de préstamos.", exception);
        }
    }

    public void goBack() {
        app.showMainView();
    }
}
