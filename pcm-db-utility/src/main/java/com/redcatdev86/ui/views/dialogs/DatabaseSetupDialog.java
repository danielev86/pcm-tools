package com.redcatdev86.ui.views.dialogs;

import com.redcatdev86.backend.DatabaseManager;

import com.redcatdev86.pcm.PcmDbService;
import com.redcatdev86.pcm.PcmPaths;
import com.redcatdev86.pcm.PcmPreferences;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public final class DatabaseSetupDialog {

    private DatabaseSetupDialog() {}

    public static boolean show(Window owner) {

        Dialog<Boolean> dialog = new Dialog<>();
        if (owner != null) dialog.initOwner(owner);

        dialog.setTitle("Database Setup");
        dialog.setHeaderText("Export CDB -> DB and load it");

        ButtonType exportBtnType = new ButtonType("Export & Load", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().setAll(exportBtnType, ButtonType.CANCEL);

        TextField tfGame = new TextField(PcmPreferences.getGameVersion());
        TextField tfUser = new TextField(PcmPreferences.getPcmUser());
        TextField tfCareer = new TextField(PcmPreferences.getCareer());

        Label lblStatus = new Label();
        lblStatus.setWrapText(true);

        TextArea taDebug = new TextArea();
        taDebug.setEditable(false);
        taDebug.setWrapText(false);
        taDebug.setPrefRowCount(10);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));

        grid.add(new Label("Game Version:"), 0, 0);
        grid.add(tfGame, 1, 0);

        grid.add(new Label("PCM User:"), 0, 1);
        grid.add(tfUser, 1, 1);

        grid.add(new Label("Career (no extension):"), 0, 2);
        grid.add(tfCareer, 1, 2);

        VBox root = new VBox(
                10,
                grid,
                new Separator(),
                new Label("Status:"),
                lblStatus,
                new Separator(),
                new Label("Debug:"),
                taDebug
        );

        dialog.getDialogPane().setContent(root);

        // Coerente con Dialog<Boolean>
        dialog.setResultConverter(bt -> bt == exportBtnType);

        Button exportButton = (Button) dialog.getDialogPane().lookupButton(exportBtnType);

        exportButton.addEventFilter(ActionEvent.ACTION, evt -> {

            String game = tfGame.getText().trim();
            String user = tfUser.getText().trim();
            String career = tfCareer.getText().trim();

            if (game.isEmpty() || user.isEmpty() || career.isEmpty()) {
                lblStatus.setText("All fields are required.");
                evt.consume();
                return;
            }

            Path exporterDir = Path.of(System.getProperty("user.dir"), "exporter");
            Path dbDataDir = Path.of(System.getProperty("user.dir"), "db_data");
            Path cdbPath = PcmPaths.cdbFile(game, user, career);
            Path expectedSqlite = PcmPaths.sqliteExportedFile(game, user, career);

            taDebug.setText(buildDebugInfo(exporterDir, dbDataDir, cdbPath, expectedSqlite));

            try {
                PcmDbService svc = new PcmDbService(exporterDir, dbDataDir);
                Path db = svc.exportAndAttach(game, user, career);

                // ✅ Salviamo i parametri PCM in un posto unico
                PcmPreferences.setAll(game, user, career);

                lblStatus.setText("Loaded DB: " + db);
                taDebug.appendText("\n\nSUCCESS.\nDatabaseManager DB path = " + DatabaseManager.getDatabasePath());

                // successo -> dialog si chiude
            } catch (Exception ex) {
                lblStatus.setText("ERROR: " + ex.getMessage());
                taDebug.appendText("\n\nEXCEPTION:\n" + stackTrace(ex));
                evt.consume(); // resta aperta
            }
        });

        Optional<Boolean> res = dialog.showAndWait();
        return res.isPresent() && res.get();
    }

    private static String buildDebugInfo(Path exporterDir, Path dbDataDir, Path cdbPath, Path expectedSqlite) {
        StringBuilder sb = new StringBuilder();
        sb.append("user.dir          = ").append(System.getProperty("user.dir")).append('\n');
        sb.append("exporterDir       = ").append(exporterDir).append(" (exists=").append(Files.exists(exporterDir)).append(")\n");
        sb.append("exporter exe .exe  = ").append(exporterDir.resolve("SQLiteExporter.exe"))
                .append(" (exists=").append(Files.exists(exporterDir.resolve("SQLiteExporter.exe"))).append(")\n");
        sb.append("exporter exe       = ").append(exporterDir.resolve("SQLiteExporter"))
                .append(" (exists=").append(Files.exists(exporterDir.resolve("SQLiteExporter"))).append(")\n");
        sb.append("dbDataDir         = ").append(dbDataDir).append(" (exists=").append(Files.exists(dbDataDir)).append(")\n");
        sb.append("cdbPath           = ").append(cdbPath).append(" (exists=").append(Files.exists(cdbPath)).append(")\n");
        sb.append("expected .sqlite   = ").append(expectedSqlite).append(" (exists=").append(Files.exists(expectedSqlite)).append(")\n");
        return sb.toString();
    }

    private static String stackTrace(Throwable t) {
        StringBuilder sb = new StringBuilder();
        sb.append(t).append('\n');
        for (StackTraceElement el : t.getStackTrace()) {
            sb.append("  at ").append(el).append('\n');
        }
        Throwable c = t.getCause();
        if (c != null) {
            sb.append("\nCaused by: ").append(stackTrace(c));
        }
        return sb.toString();
    }
}