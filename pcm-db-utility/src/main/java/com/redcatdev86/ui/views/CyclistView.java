package com.redcatdev86.ui.views;

import com.redcatdev86.service.CyclistService;
import com.redcatdev86.service.TeamService;
import com.redcatdev86.ui.model.CyclistBean;
import com.redcatdev86.ui.model.TeamBean;
import com.redcatdev86.ui.views.dialogs.CyclistDetailsDialog;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CyclistView implements AppView {

    private final BorderPane root = new BorderPane();
    private final TableView<CyclistBean> table = new TableView<>();

    // teniamo TUTTO in memoria
    private final ObservableList<CyclistBean> masterData = FXCollections.observableArrayList();
    private final FilteredList<CyclistBean> filteredData = new FilteredList<>(masterData, c -> true);

    private final ObservableList<TeamBean> teamItems = FXCollections.observableArrayList();
    private final Map<Integer, String> teamNameMap = new HashMap<>();

    private final CyclistService cyclistService = new CyclistService();
    private final TeamService teamService = new TeamService();

    private TextField txtSearch;
    private ComboBox<TeamBean> cmbTeam;
    private TextField txtMinCA;
    private TextField txtMinPOT;

    public CyclistView() {
        root.setPadding(new Insets(12));
        root.setTop(buildTopBar());
        root.setCenter(buildTable());

        loadTeams();
        loadData(); // 1 botta sola
    }

    @Override
    public String getTitle() {
        return "View Cyclist";
    }

    @Override
    public Node getRoot() {
        return root;
    }

    private Node buildTopBar() {
        Label title = new Label(getTitle());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label lblSearch = new Label("Search:");
        txtSearch = new TextField();
        txtSearch.setPrefWidth(220);
        txtSearch.setPromptText("first/last name...");
        txtSearch.textProperty().addListener((obs, o, n) -> applyFilters());
        HBox searchBox = new HBox(8, lblSearch, txtSearch);

        Label lblTeam = new Label("Team:");
        cmbTeam = new ComboBox<>();
        cmbTeam.setPrefWidth(260);
        cmbTeam.setPromptText("All teams");
        cmbTeam.valueProperty().addListener((obs, o, n) -> applyFilters());
        HBox teamBox = new HBox(8, lblTeam, cmbTeam);

        Label lblMinCA = new Label("Min CA:");
        txtMinCA = new TextField();
        txtMinCA.setPrefWidth(80);
        txtMinCA.setPromptText("70");
        txtMinCA.textProperty().addListener((obs, o, n) -> applyFilters());
        HBox caBox = new HBox(8, lblMinCA, txtMinCA);

        Label lblMinPOT = new Label("Min POT:");
        txtMinPOT = new TextField();
        txtMinPOT.setPrefWidth(80);
        txtMinPOT.setPromptText("80");
        txtMinPOT.textProperty().addListener((obs, o, n) -> applyFilters());
        HBox potBox = new HBox(8, lblMinPOT, txtMinPOT);

        Button btnClear = new Button("Clear");
        btnClear.setOnAction(e -> {
            txtSearch.clear();
            cmbTeam.setValue(null);
            txtMinCA.clear();
            txtMinPOT.clear();
            applyFilters();
        });

        HBox filtersRow = new HBox(20, searchBox, teamBox, caBox, potBox, btnClear);
        filtersRow.setPadding(new Insets(10, 0, 16, 0));

        Separator sep = new Separator();
        return new VBox(6, title, filtersRow, sep);
    }

    private Node buildTable() {

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Colonna azione 🔍
        TableColumn<CyclistBean, Void> colAction = new TableColumn<>("");
        colAction.setPrefWidth(55);
        colAction.setMinWidth(55);
        colAction.setMaxWidth(55);

        colAction.setCellFactory(tc -> new TableCell<>() {
            private final Button btn = new Button("🔍");

            {
                btn.setOnAction(e -> {
                    CyclistBean c = getTableView().getItems().get(getIndex());
                    openDetails(c);
                });
                btn.setFocusTraversable(false);
                btn.setStyle("-fx-font-size: 14px;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        // Nome
        TableColumn<CyclistBean, String> colFirst = new TableColumn<>("Nome");
        colFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colFirst.setPrefWidth(180);

        // Cognome
        TableColumn<CyclistBean, String> colLast = new TableColumn<>("Cognome");
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colLast.setPrefWidth(200);

        // Team (derivato dalla mappa)
        TableColumn<CyclistBean, String> colTeam = new TableColumn<>("Team");
        colTeam.setCellValueFactory(cell -> {
            String name = teamNameMap.get(cell.getValue().getFkIdTeam());
            return new SimpleStringProperty(name == null ? "" : name);
        });
        colTeam.setPrefWidth(300);

        // CA
        TableColumn<CyclistBean, Double> colCA = new TableColumn<>("CA");
        colCA.setCellValueFactory(new PropertyValueFactory<>("currentAbility"));
        colCA.setPrefWidth(80);

        // POT
        TableColumn<CyclistBean, Double> colPot = new TableColumn<>("Potenziale");
        colPot.setCellValueFactory(new PropertyValueFactory<>("potential"));
        colPot.setPrefWidth(100);

        table.getColumns().setAll(
                colAction,
                colFirst,
                colLast,
                colTeam,
                colCA,
                colPot
        );

        SortedList<CyclistBean> sorted = new SortedList<>(filteredData);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        return table;
    }

    private void openDetails(CyclistBean c) {
        String teamName = teamNameMap.get(c.getFkIdTeam());
        CyclistDetailsDialog d = new CyclistDetailsDialog(c, teamName);
        d.initOwner(root.getScene() != null ? root.getScene().getWindow() : null);
        d.showAndWait();
    }

    private void loadData() {
        // carichiamo tutto "full" in pancia
        List<CyclistBean> cyclists = cyclistService.getAllCyclists();
        masterData.setAll(cyclists);
        applyFilters();
    }

    private void loadTeams() {
        List<TeamBean> teams = teamService.getAllTeams();
        teamItems.setAll(teams);
        cmbTeam.setItems(teamItems);

        teamNameMap.clear();
        for (TeamBean t : teams) {
            teamNameMap.put(t.getIdTeam(), t.getName());
        }
    }

    private void applyFilters() {
        String query = txtSearch.getText() == null ? "" : txtSearch.getText().trim().toLowerCase();

        TeamBean selectedTeam = cmbTeam.getValue();
        Integer selectedTeamId = selectedTeam == null ? null : selectedTeam.getIdTeam();

        Double minCA = parseDoubleOrNull(txtMinCA.getText());
        Double minPOT = parseDoubleOrNull(txtMinPOT.getText());

        filteredData.setPredicate(c -> {
            boolean textOk = query.isEmpty()
                    || (c.getFirstName() != null && c.getFirstName().toLowerCase().contains(query))
                    || (c.getLastName() != null && c.getLastName().toLowerCase().contains(query))
                    || (c.getFirstLastName() != null && c.getFirstLastName().toLowerCase().contains(query));
            if (!textOk) return false;

            if (selectedTeamId != null && c.getFkIdTeam() != selectedTeamId) return false;

            if (minCA != null) {
                Double ca = c.getCurrentAbility();
                if (ca == null || ca < minCA) return false;
            }

            if (minPOT != null) {
                Double pot = c.getPotential();
                if (pot == null || pot < minPOT) return false;
            }

            return true;
        });
    }

    private Double parseDoubleOrNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        if (t.isEmpty()) return null;
        try {
            return Double.parseDouble(t);
        } catch (Exception ex) {
            return null;
        }
    }
}