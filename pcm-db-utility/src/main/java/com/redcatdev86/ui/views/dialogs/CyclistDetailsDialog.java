package com.redcatdev86.ui.views.dialogs;

import com.redcatdev86.service.CyclistService;
import com.redcatdev86.ui.model.CyclistBean;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CyclistDetailsDialog extends Dialog<Void> {

    private final ObservableList<SkillRow> rows = FXCollections.observableArrayList();
    private final Label validationMsg = new Label();

    private final CyclistService cyclistService = new CyclistService();

    private final CyclistBean cyclist;
    private final String teamName;

    public CyclistDetailsDialog(CyclistBean c, String teamName) {
        this.cyclist = c;
        this.teamName = teamName;

        setTitle("Cyclist Details");
        setHeaderText(null);
        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        // fixed size to reduce layout flicker
        getDialogPane().setMinWidth(720);
        getDialogPane().setPrefWidth(720);
        getDialogPane().setMinHeight(680);
        getDialogPane().setPrefHeight(680);

        VBox root = new VBox(12);
        root.setPadding(new Insets(12));

        Label title = new Label(safe(c.getFirstLastName()));
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        GridPane info = new GridPane();
        info.setHgap(12);
        info.setVgap(6);

        int r = 0;
        r = addRow(info, r, "ID", String.valueOf(c.getIdCyclist()));
        r = addRow(info, r, "First Name", safe(c.getFirstName()));
        r = addRow(info, r, "Last Name", safe(c.getLastName()));
        r = addRow(info, r, "Team", teamName == null ? "" : teamName);
        r = addRow(info, r, "CA", String.valueOf(c.getCurrentAbility()));
        r = addRow(info, r, "Potential", String.valueOf(c.getPotential()));

        Label skillsTitle = new Label("Skills");
        skillsTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        skillsTitle.setPadding(new Insets(6, 0, 0, 0));

        validationMsg.setStyle("-fx-text-fill: #b00020; -fx-font-size: 12px;");
        validationMsg.setWrapText(true);

        TableView<SkillRow> table = new TableView<>();
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        TableColumn<SkillRow, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(x -> x.getValue().nameProperty());
        colName.setPrefWidth(260);

        TableColumn<SkillRow, Integer> colReal = new TableColumn<>("Real");
        colReal.setCellValueFactory(x -> x.getValue().realProperty().asObject());
        colReal.setPrefWidth(140);

        StringConverter<Integer> intConverter = new StringConverter<>() {
            @Override public String toString(Integer value) {
                return value == null ? "" : String.valueOf(value);
            }
            @Override public Integer fromString(String s) {
                if (s == null) return null;
                String t = s.trim();
                if (t.isEmpty()) return null;
                try { return Integer.parseInt(t); } catch (Exception ex) { return null; }
            }
        };

        colReal.setCellFactory(TextFieldTableCell.forTableColumn(intConverter));
        colReal.setOnEditCommit(evt -> {
            SkillRow row = evt.getRowValue();
            int oldVal = evt.getOldValue() == null ? row.getReal() : evt.getOldValue();
            Integer newObj = evt.getNewValue();

            validationMsg.setText("");

            if (newObj == null) {
                row.setReal(oldVal);
                return;
            }

            int newVal = newObj;

            if (newVal < 0 || newVal > 99) {
                row.setReal(oldVal);
                validationMsg.setText("Invalid value: Real must be between 0 and 99.");
                return;
            }

            if (newVal > row.getPotential()) {
                row.setReal(oldVal);
                validationMsg.setText("Invalid value: Real cannot be greater than Potential.");
                return;
            }

            row.setReal(newVal);
        });

        TableColumn<SkillRow, Integer> colPot = new TableColumn<>("Potential");
        colPot.setCellValueFactory(x -> x.getValue().potentialProperty().asObject());
        colPot.setPrefWidth(160);

        table.getColumns().addAll(colName, colReal, colPot);

        // Fill rows from CyclistBean (Real=charac, Potential=limit)
        rows.clear();
        addSkill("Plain", c.getCharPlain(), c.getLimitPlain());
        addSkill("Mountain", c.getCharMountain(), c.getLimitMountain());
        addSkill("Medium Mountain", c.getCharMediumMountain(), c.getLimitMediumMountain());
        addSkill("Downhilling", c.getCharDownhilling(), c.getLimitDownhilling());
        addSkill("Cobble", c.getCharCobble(), c.getLimitCobble());
        addSkill("TimeTrial", c.getCharTimeTrial(), c.getLimitTimeTrial());
        addSkill("Prologue", c.getCharPrologue(), c.getLimitPrologue());
        addSkill("Sprint", c.getCharSprint(), c.getLimitSprint());
        addSkill("Acceleration", c.getCharAcceleration(), c.getLimitAcceleration());
        addSkill("Endurance", c.getCharEndurance(), c.getLimitEndurance());
        addSkill("Resistance", c.getCharResistance(), c.getLimitResistance());
        addSkill("Recuperation", c.getCharRecuperation(), c.getLimitRecuperation());
        addSkill("Hill", c.getCharHill(), c.getLimitHill());
        addSkill("Baroudeur", c.getCharBaroudeur(), c.getLimitBaroudeur());

        table.setItems(rows);

        // Save button + confirm popup
        Button btnSave = new Button("Save");
        btnSave.setOnAction(e -> onSave());

        Button btnReset = new Button("Reset");
        btnReset.setOnAction(e -> {
            validationMsg.setText("");
            rows.forEach(SkillRow::resetToInitial);
        });

        HBox actions = new HBox(10, btnSave, btnReset);
        actions.setPadding(new Insets(6, 0, 0, 0));

        root.getChildren().addAll(title, info, skillsTitle, table, validationMsg, actions);
        getDialogPane().setContent(root);
    }

    private void onSave() {
        validationMsg.setText("");

        // extra safety check (in case some row slipped through)
        for (SkillRow r : rows) {
            if (r.getReal() > r.getPotential()) {
                validationMsg.setText("Cannot save: at least one Real value is greater than Potential.");
                return;
            }
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Save");
        confirm.setHeaderText("Save changes?");
        confirm.setContentText("Are you sure you want to save the updated skills for this cyclist?");
        ButtonType ok = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirm.getButtonTypes().setAll(ok, cancel);

        var res = confirm.showAndWait();
        if (res.isEmpty() || res.get() != ok) return;

        // Build map name->real (keeps order, but not required)
        Map<String, Integer> m = new LinkedHashMap<>();
        for (SkillRow r : rows) {
            m.put(r.getName(), r.getReal());
        }

        try {
            // update DB
            cyclistService.updateSkills(
                    cyclist.getIdCyclist(),
                    m.get("Plain"),
                    m.get("Mountain"),
                    m.get("Medium Mountain"),
                    m.get("Downhilling"),
                    m.get("Cobble"),
                    m.get("TimeTrial"),
                    m.get("Prologue"),
                    m.get("Sprint"),
                    m.get("Acceleration"),
                    m.get("Endurance"),
                    m.get("Resistance"),
                    m.get("Recuperation"),
                    m.get("Hill"),
                    m.get("Baroudeur")
            );

            // update in-memory bean (so UI remains consistent without reloading)
            applyToBean(cyclist, m);

            Alert okAlert = new Alert(Alert.AlertType.INFORMATION);
            okAlert.setTitle("Saved");
            okAlert.setHeaderText("Saved successfully");
            okAlert.setContentText("Skills updated.");
            okAlert.showAndWait();

            // close dialog
            close();

        } catch (Exception ex) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("Error");
            err.setHeaderText("Save failed");
            err.setContentText(ex.getMessage());
            err.showAndWait();
        }
    }

    private void applyToBean(CyclistBean c, Map<String, Integer> m) {
        // Requires setters in CyclistBean. If you don’t have them yet, add them (see note below).
        c.setCharPlain(m.get("Plain"));
        c.setCharMountain(m.get("Mountain"));
        c.setCharMediumMountain(m.get("Medium Mountain"));
        c.setCharDownhilling(m.get("Downhilling"));
        c.setCharCobble(m.get("Cobble"));
        c.setCharTimeTrial(m.get("TimeTrial"));
        c.setCharPrologue(m.get("Prologue"));
        c.setCharSprint(m.get("Sprint"));
        c.setCharAcceleration(m.get("Acceleration"));
        c.setCharEndurance(m.get("Endurance"));
        c.setCharResistance(m.get("Resistance"));
        c.setCharRecuperation(m.get("Recuperation"));
        c.setCharHill(m.get("Hill"));
        c.setCharBaroudeur(m.get("Baroudeur"));
    }

    // --- Row Model ---
    public static class SkillRow {
        private final StringProperty name = new SimpleStringProperty();
        private final IntegerProperty initialReal = new SimpleIntegerProperty();
        private final IntegerProperty real = new SimpleIntegerProperty();
        private final IntegerProperty potential = new SimpleIntegerProperty();

        public SkillRow(String name, int real, int potential) {
            this.name.set(name);
            this.initialReal.set(real);
            this.real.set(real);
            this.potential.set(potential);
        }

        public StringProperty nameProperty() { return name; }
        public IntegerProperty realProperty() { return real; }
        public IntegerProperty potentialProperty() { return potential; }

        public String getName() { return name.get(); }
        public int getReal() { return real.get(); }
        public void setReal(int v) { real.set(v); }
        public int getPotential() { return potential.get(); }

        public void resetToInitial() {
            real.set(initialReal.get());
        }
    }

    private void addSkill(String name, Integer real, Integer pot) {
        int r = real == null ? 0 : real;
        int p = pot == null ? 0 : pot;
        rows.add(new SkillRow(name, r, p));
    }

    private int addRow(GridPane gp, int row, String label, String value) {
        gp.add(new Label(label + ":"), 0, row);
        gp.add(new Label(value == null ? "" : value), 1, row);
        return row + 1;
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}