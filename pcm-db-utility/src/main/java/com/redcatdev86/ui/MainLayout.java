package com.redcatdev86.ui;

import com.redcatdev86.backend.DatabaseManager;
import com.redcatdev86.pcm.PcmDbService;
import com.redcatdev86.pcm.PcmPreferences;
import com.redcatdev86.ui.views.*;
import com.redcatdev86.ui.views.dialogs.DatabaseSetupDialog;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Window;

import java.nio.file.Path;

public class MainLayout {

    private final BorderPane root = new BorderPane();
    private final StackPane contentPane = new StackPane();

    private final Label dbStatus = new Label();

    private final ToggleGroup menuGroup = new ToggleGroup();

    // Lazy views
    private CyclistView cyclistView;
    private TeamView teamView;
    private ScoutView scoutView;
    private CoachView coachView;

    public MainLayout() {
        root.setTop(buildNavbar());
        root.setLeft(buildSidebar());
        root.setCenter(contentPane);

        // placeholder iniziale
        Label welcome = new Label("Select a page from the left menu.");
        welcome.getStyleClass().add("muted");
        contentPane.getChildren().setAll(welcome);

        refreshDbLabel();
    }

    public Parent getRoot() {
        return root;
    }

    // ================= NAVBAR (Bootstrap-like) =================

    private Node buildNavbar() {

        // Brand
        Label brand = new Label("PCM DB By Redcatdev86");
        brand.getStyleClass().add("navbar-brand");

        // Buttons
        Button btnOpen = new Button("Open DB");
        btnOpen.getStyleClass().addAll("button", "outline");
        btnOpen.setOnAction(e -> onOpenDb(btnOpen.getScene().getWindow()));

        Button btnSaveAll = new Button("Save All");
        btnSaveAll.getStyleClass().addAll("button", "primary");
        btnSaveAll.setOnAction(e -> onSaveAll(btnSaveAll.getScene().getWindow()));

        // DB status
        dbStatus.getStyleClass().add("muted");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox right = new HBox(10, btnOpen, btnSaveAll, dbStatus);
        right.setPadding(new Insets(0));

        HBox navbar = new HBox(15, brand, spacer, right);
        navbar.setPadding(new Insets(12));
        navbar.getStyleClass().add("navbar");

        return navbar;
    }

    private void refreshDbLabel() {
        if (DatabaseManager.isConfigured()) {
            dbStatus.setText("DB: loaded");
        } else {
            dbStatus.setText("DB: not loaded");
        }
    }

    // ================= SIDEBAR (Bootstrap nav-pills) =================

    private Node buildSidebar() {

        VBox sidebar = new VBox(6);
        sidebar.setPadding(new Insets(12));
        sidebar.setPrefWidth(210);
        sidebar.getStyleClass().add("sidebar");

        Label navTitle = new Label("Navigation");
        navTitle.getStyleClass().add("sidebar-title");

        ToggleButton btnCyclists = createNavPill("🚴  Cyclists", this::openCyclist);
        ToggleButton btnTeams    = createNavPill("📊  Teams", this::openTeam);
        ToggleButton btnScouts   = createNavPill("👥  Scouts", this::openScout);
        ToggleButton btnCoaches  = createNavPill("🧭  Coaches", this::openCoach);

        sidebar.getChildren().addAll(navTitle, btnCyclists, btnTeams, btnScouts, btnCoaches);

        // Se vuoi selezione di default (opzionale):
        // btnCyclists.setSelected(true);

        return sidebar;
    }

    private ToggleButton createNavPill(String text, Runnable action) {

        ToggleButton btn = new ToggleButton(text);
        btn.setToggleGroup(menuGroup);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.getStyleClass().add("nav-pill");

        // quando seleziono, apro la view
        btn.selectedProperty().addListener((obs, was, is) -> {
            if (is) action.run();
        });

        return btn;
    }

    // ================= OPEN VIEWS =================

    private void ensureDbLoaded() {
        if (!DatabaseManager.isConfigured()) {
            new Alert(Alert.AlertType.WARNING, "Load a database first.").showAndWait();
            throw new IllegalStateException("DB not configured");
        }
    }

    private void openCyclist() {
        ensureDbLoaded();
        if (cyclistView == null) cyclistView = new CyclistView();
        contentPane.getChildren().setAll(cyclistView.getRoot());
    }

    private void openTeam() {
        ensureDbLoaded();
        if (teamView == null) teamView = new TeamView();
        contentPane.getChildren().setAll(teamView.getRoot());
    }

    private void openScout() {
        ensureDbLoaded();
        if (scoutView == null) scoutView = new ScoutView();
        contentPane.getChildren().setAll(scoutView.getRoot());
    }

    private void openCoach() {
        ensureDbLoaded();
        if (coachView == null) coachView = new CoachView();
        contentPane.getChildren().setAll(coachView.getRoot());
    }

    // ================= ACTIONS =================

    private void onOpenDb(Window owner) {
        boolean ok = DatabaseSetupDialog.show(owner);
        if (ok) {
            refreshDbLabel();

            // reset cache view (per ricaricare dal nuovo DB)
            cyclistView = null;
            teamView = null;
            scoutView = null;
            coachView = null;

            contentPane.getChildren().setAll(new Label("Database loaded. Select a page."));
            menuGroup.selectToggle(null);
        }
    }

    private void onSaveAll(Window owner) {

        if (!DatabaseManager.isConfigured()) {
            new Alert(Alert.AlertType.WARNING, "Load a database first.").showAndWait();
            return;
        }

        if (!PcmPreferences.isComplete()) {
            new Alert(Alert.AlertType.WARNING, "Missing PCM info. Open DB first.").showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Save All");
        confirm.setHeaderText("Export all changes to CDB?");
        confirm.setContentText("This will overwrite the original career file.");
        if (confirm.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) return;

        try {
            Path exporterDir = Path.of(System.getProperty("user.dir"), "exporter");
            Path dbDataDir = Path.of(System.getProperty("user.dir"), "db_data");

            PcmDbService svc = new PcmDbService(exporterDir, dbDataDir);
            svc.saveAllToCdb(
                    PcmPreferences.getGameVersion(),
                    PcmPreferences.getPcmUser(),
                    PcmPreferences.getCareer()
            );

            // after save: Continue OR Change DB
            ButtonType cont = new ButtonType("Continue editing");
            ButtonType change = new ButtonType("Change database");

            Alert done = new Alert(Alert.AlertType.CONFIRMATION);
            done.setTitle("Saved");
            done.setHeaderText("CDB updated successfully.");
            done.setContentText("What do you want to do now?");
            done.getButtonTypes().setAll(cont, change);

            if (done.showAndWait().orElse(cont) == change) {
                onOpenDb(owner);
            }

        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Save failed:\n" + ex.getMessage()).showAndWait();
        }
    }
}