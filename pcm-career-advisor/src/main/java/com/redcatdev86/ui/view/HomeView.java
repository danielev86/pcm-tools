package com.redcatdev86.ui.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeView extends VBox {

    public HomeView() {
        getStyleClass().addAll("page-box", "page-home");
        setAlignment(Pos.TOP_LEFT);
        setSpacing(24);

        Label heroTitle = new Label("PCM Career Advisor");
        heroTitle.getStyleClass().add("hero-title");

        Label heroSubtitle = new Label("""
                A lightweight tool for Pro Cycling Manager career ideas.
                Use Team Chooser to get a random team suggestion or Team Builder to generate a sponsor profile.
                """);
        heroSubtitle.getStyleClass().add("hero-subtitle");
        heroSubtitle.setWrapText(true);

        VBox heroBox = new VBox(10, heroTitle, heroSubtitle);
        heroBox.getStyleClass().add("hero-box");

        VBox card1 = createCard(
                "Home",
                "Start from here and navigate through the main tools of the app."
        );

        VBox card2 = createCard(
                "Team Chooser",
                "Browse and filter teams, then rank them with a random score."
        );

        VBox card3 = createCard(
                "Team Builder",
                "Generate a sponsor profile using continent, country and region filters."
        );

        HBox cardsRow = new HBox(18, card1, card2, card3);
        cardsRow.getStyleClass().add("dashboard-row");

        getChildren().addAll(heroBox, cardsRow);
    }

    private VBox createCard(String titleText, String descriptionText) {
        Label title = new Label(titleText);
        title.getStyleClass().add("dashboard-card-title");

        Label description = new Label(descriptionText);
        description.getStyleClass().add("dashboard-card-text");
        description.setWrapText(true);

        VBox card = new VBox(10, title, description);
        card.getStyleClass().add("dashboard-card");
        card.setPrefWidth(260);
        card.setMinHeight(170);

        return card;
    }
}