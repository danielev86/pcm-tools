package com.redcatdev86.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class LeftMenu extends VBox {

    private final Map<String, Button> menuButtons = new HashMap<>();

    public LeftMenu(Consumer<String> onNavigate) {
        getStyleClass().add("left-menu");
        setAlignment(Pos.TOP_CENTER);

        Button homeButton = createMenuButton("Home", "HOME", onNavigate);
        Button chooserButton = createMenuButton("Team Chooser", "TEAM_CHOOSER", onNavigate);
        Button builderButton = createMenuButton("Team Builder", "TEAM_BUILDER", onNavigate);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        getChildren().addAll(
                homeButton,
                chooserButton,
                builderButton,
                spacer
        );
    }

    private Button createMenuButton(String text, String key, Consumer<String> onNavigate) {
        Button button = new Button(text);
        button.getStyleClass().add("menu-button");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> onNavigate.accept(key));

        menuButtons.put(key, button);
        return button;
    }

    public void setActive(String key) {
        for (Button button : menuButtons.values()) {
            button.getStyleClass().remove("menu-button-active");
        }

        Button activeButton = menuButtons.get(key);
        if (activeButton != null && !activeButton.getStyleClass().contains("menu-button-active")) {
            activeButton.getStyleClass().add("menu-button-active");
        }
    }
}