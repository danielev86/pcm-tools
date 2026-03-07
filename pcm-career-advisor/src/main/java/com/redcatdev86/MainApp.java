package com.redcatdev86;

import com.redcatdev86.ui.MainLayout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        MainLayout root = new MainLayout();
        Scene scene = new Scene(root, 1200, 750);

        URL cssUrl = getClass().getResource("/css/app.css");
        if (cssUrl == null) {
            throw new IllegalStateException("CSS non trovato: /css/app.css");
        }

        scene.getStylesheets().add(cssUrl.toExternalForm());

        stage.setTitle("PCM Career Advisor");
        stage.setMinWidth(1000);
        stage.setMinHeight(650);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}