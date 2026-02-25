package com.redcatdev86.ui.views;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MiscView implements AppView {

    private final VBox root;

    public MiscView() {
        Label title = new Label(getTitle());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label hint = new Label("Contenuto Miscellaneous (impostazioni, tools, ecc.)");
        hint.setStyle("-fx-opacity: 0.8;");

        root = new VBox(10, title, hint);
        root.setPadding(new Insets(16));
    }

    @Override
    public String getTitle() {
        return "Miscellaneous";
    }

    @Override
    public Node getRoot() {
        return root;
    }
}