package com.redcatdev86.ui;

import com.redcatdev86.ui.view.HomeView;
import com.redcatdev86.ui.view.TeamBuilderView;
import com.redcatdev86.ui.view.TeamChooserView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MainLayout extends BorderPane {

    private final StackPane contentPane;
    private final LeftMenu leftMenu;
    private final Label pageTitleLabel;

    public MainLayout() {
        getStyleClass().add("app-root");

        pageTitleLabel = new Label("PCM Career Advisor");
        pageTitleLabel.getStyleClass().add("app-title");

        HBox topBar = new HBox(pageTitleLabel);
        topBar.getStyleClass().add("top-bar");

        contentPane = new StackPane();
        contentPane.getStyleClass().add("content-pane");

        leftMenu = new LeftMenu(this::navigateTo);

        setTop(topBar);
        setLeft(leftMenu);
        setCenter(contentPane);

        navigateTo("HOME");
    }

    private void navigateTo(String page) {
        Node view = switch (page) {
            case "TEAM_CHOOSER" -> new TeamChooserView();
            case "TEAM_BUILDER" -> new TeamBuilderView();
            case "HOME" -> new HomeView();
            default -> new HomeView();
        };

        String pageTitle = "PCM Career Advisor";

        pageTitleLabel.setText(pageTitle);
        contentPane.getChildren().setAll(view);
        leftMenu.setActive(page);
    }
}