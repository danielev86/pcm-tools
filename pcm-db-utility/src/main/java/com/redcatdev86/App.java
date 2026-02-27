package com.redcatdev86;

import com.redcatdev86.backend.DatabaseManager;
import com.redcatdev86.ui.MainLayout;
import com.redcatdev86.ui.views.dialogs.DatabaseSetupDialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        MainLayout layout = new MainLayout();
        stage.setTitle("PCM DB By Redcatdev86");
        Scene scene = new Scene(layout.getRoot(), 1200, 800);
        scene.getStylesheets().add(
                getClass().getResource("/style/pcm.css").toExternalForm()
        );
        stage.setScene(scene);
        stage.show();

        if (!DatabaseManager.loadDatabasePathFromPreferences()) {
            DatabaseSetupDialog.show(stage);
        }
    }
}