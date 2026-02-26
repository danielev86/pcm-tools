package com.redcatdev86.ui;

import com.redcatdev86.ui.views.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainLayout {

    private final BorderPane root;

    public MainLayout() {
        root = new BorderPane();
        root.setLeft(createSideMenu());

        // view di default
        setCenterView(new CyclistView());
    }

    public BorderPane getRoot() {
        return root;
    }

    private VBox createSideMenu() {

        Button btnCyclist = new Button("View Cyclist");
        Button btnTeam = new Button("View Team");
        Button btnScout = new Button("View Scout");
        Button btnYoungRider = new Button("View Young Rider");
        Button btnGamUser = new Button("View Gam User");
        Button btnCoach = new Button("View Coach");
        Button btnMisc = new Button("Miscellaneous");

        for (Button b : new Button[]{btnCyclist, btnTeam, btnScout, btnCoach, btnYoungRider, btnGamUser,btnMisc}) {
            b.setMaxWidth(Double.MAX_VALUE);
            b.setPrefHeight(40);
        }

        btnCyclist.setOnAction(e -> setCenterView(new CyclistView()));
        btnTeam.setOnAction(e -> setCenterView(new TeamView()));
        btnScout.setOnAction(e -> setCenterView(new ScoutView()));
        btnCoach.setOnAction(e -> setCenterView(new CoachView()));
        btnYoungRider.setOnAction(e -> setCenterView(new YoungRiderView()));
        btnGamUser.setOnAction(e -> setCenterView(new GamUserView()));
        btnMisc.setOnAction(e -> setCenterView(new MiscView()));

        VBox menu = new VBox(10,
                new Label("MENU"),
                new Separator(),
                btnCyclist,
                btnTeam,
                btnScout,
                btnCoach,
                btnYoungRider,
                btnGamUser,
                btnMisc
        );

        menu.setPadding(new Insets(12));
        menu.setPrefWidth(220);
        menu.setStyle("-fx-background-color: #f3f3f3;");

        VBox.setVgrow(btnMisc, Priority.NEVER);
        return menu;
    }

    private void setCenterView(AppView view) {
        Node content = view.getRoot();
        root.setCenter(content);
    }
}