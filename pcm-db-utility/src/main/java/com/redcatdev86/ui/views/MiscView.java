package com.redcatdev86.ui.views;

import com.redcatdev86.service.MiscService;
import com.redcatdev86.service.TeamService;
import com.redcatdev86.ui.model.TeamBean;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.util.List;

public class MiscView implements AppView {

    private final VBox root = new VBox(14);

    private final TeamService teamService = new TeamService();
    private final MiscService miscService = new MiscService();

    private ComboBox<TeamBean> cmbTeam;
    private Button btnSaveFatigue;
    private Button btnSaveTransfer;

    public MiscView() {
        root.setPadding(new Insets(12));
        root.getChildren().addAll(
                buildTitle(),
                buildFatigueBlock(),
                new Separator(),
                buildTransferBlock()
        );

        loadTeams();
    }

    @Override
    public String getTitle() {
        return "Misc";
    }

    @Override
    public Node getRoot() {
        return root;
    }

    private Node buildTitle() {
        Label title = new Label("Misc");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        return title;
    }

    private Node buildFatigueBlock() {
        Label lbl = new Label("Reset fatigue for cyclist team");
        lbl.setStyle("-fx-font-size: 14px; -fx-font-weight: 600;");

        Label lblTeam = new Label("Team:");
        cmbTeam = new ComboBox<>();
        cmbTeam.setPrefWidth(420);
        cmbTeam.setPromptText("Select a team...");

        // ✅ mostra "Team Name (ID: 123)"
        cmbTeam.setConverter(new StringConverter<>() {
            @Override
            public String toString(TeamBean t) {
                if (t == null) return "";
                String name = t.getName();
                try {
                    if (name == null || name.isBlank()) name = t.getShortName();
                } catch (Exception ignored) {}
                if (name == null || name.isBlank()) name = "Team";
                return name + " (ID: " + t.getIdTeam() + ")";
            }

            @Override
            public TeamBean fromString(String s) {
                return null;
            }
        });

        cmbTeam.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(TeamBean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : cmbTeam.getConverter().toString(item));
            }
        });

        btnSaveFatigue = new Button("Save fatigue");
        btnSaveFatigue.setOnAction(e -> onSaveFatigue());

        HBox row = new HBox(10, lblTeam, cmbTeam, btnSaveFatigue);
        row.setPadding(new Insets(8, 0, 0, 0));

        return new VBox(6, lbl, row);
    }

    private Node buildTransferBlock() {
        Label lbl = new Label("All cyclist available to transfer");
        lbl.setStyle("-fx-font-size: 14px; -fx-font-weight: 600;");

        btnSaveTransfer = new Button("Save transfer");
        btnSaveTransfer.setOnAction(e -> onSaveTransfer());

        HBox row = new HBox(10, btnSaveTransfer);
        row.setPadding(new Insets(8, 0, 0, 0));

        return new VBox(6, lbl, row);
    }

    private void loadTeams() {
        List<TeamBean> teams = teamService.getAllTeams();
        cmbTeam.getItems().setAll(teams);
    }

    private void onSaveFatigue() {
        TeamBean t = cmbTeam.getValue();
        if (t == null) {
            alert(Alert.AlertType.WARNING, "Missing team", "Select a team first.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm");
        confirm.setHeaderText("Reset fatigue for team?");
        confirm.setContentText("Team: " + cmbTeam.getConverter().toString(t));

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) return;

        try {
            int updated = miscService.resetFatigueForTeam(t.getIdTeam());
            alert(Alert.AlertType.INFORMATION, "Success",
                    "Fatigue reset completed.\nRows updated: " + updated);
        } catch (Exception ex) {
            alert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
    }

    private void onSaveTransfer() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm");
        confirm.setHeaderText("Set ALL cyclists available to transfer?");
        confirm.setContentText("This will run: UPDATE DYN_transfer_available_cyclist SET gene_f_interest = 100");

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) return;

        try {
            int updated = miscService.setAllCyclistsAvailableToTransfer();
            alert(Alert.AlertType.INFORMATION, "Success",
                    "Transfer availability updated.\nRows updated: " + updated);
        } catch (Exception ex) {
            alert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
    }

    private void alert(Alert.AlertType type, String header, String content) {
        Alert a = new Alert(type);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }
}