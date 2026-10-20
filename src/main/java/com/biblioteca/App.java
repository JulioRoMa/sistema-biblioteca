package com.biblioteca;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private Stage window;

    @Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;
        window.setTitle("Sistema de Biblioteca");
        showMainView();
        window.show();
    }

    public void showMainView() {
        window.setScene(new MainView(this).getScene());
    }

    public void showView(Scene scene) {
        window.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
