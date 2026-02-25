package com.redcatdev86;

import com.redcatdev86.ui.MainLayout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainLayout mainLayout = new MainLayout();

        Scene scene = new Scene(mainLayout.getRoot(), 900, 550);
        stage.setTitle("Cycling Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}