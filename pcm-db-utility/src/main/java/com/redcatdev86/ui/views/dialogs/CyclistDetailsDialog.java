package com.redcatdev86.ui.views.dialogs;

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
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class CyclistDetailsDialog extends Dialog<Void> {

    private final ObservableList<SkillRow> rows = FXCollections.observableArrayList();
    private final Label validationMsg = new Label();

    public CyclistDetailsDialog(CyclistBean c, String teamName) {

        setTitle("Cyclist Details");
        setHeaderText(null);
        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        // dimensioni fisse -> evita “saltelli” di layout
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
        r = addRow(info, r, "CA", c.getCurrentAbility() == null ? "" : String.valueOf(c.getCurrentAbility()));
        r = addRow(info, r, "Potential", c.getPotential() == null ? "" : String.valueOf(c.getPotential()));

        Label skillsTitle = new Label("Skills");
        skillsTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        skillsTitle.setPadding(new Insets(6, 0, 0, 0));

        // messaggio inline (niente alert -> niente focus jump)
        validationMsg.setStyle("-fx-text-fill: #b00020; -fx-font-size: 12px;");
        validationMsg.setWrapText(true);

        TableView<SkillRow> table = new TableView<>();
        table.setEditable(true);

        // molte tabelle “flickerano” con constrained dentro dialog -> usiamo unconstrained
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        TableColumn<SkillRow, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(x -> x.getValue().nameProperty());
        colName.setPrefWidth(260);

        TableColumn<SkillRow, Integer> colReal = new TableColumn<>("Real");
        colReal.setCellValueFactory(x -> x.getValue().realProperty().asObject());
        colReal.setPrefWidth(140);

        // Converter robusto: se input vuoto o non numerico, ripristina senza eccezioni
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

            // reset messaggio
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

        // Fill rows
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

        root.getChildren().addAll(title, info, skillsTitle, table, validationMsg);
        getDialogPane().setContent(root);
    }

    // --- Row Model ---
    public static class SkillRow {
        private final StringProperty name = new SimpleStringProperty();
        private final IntegerProperty real = new SimpleIntegerProperty();
        private final IntegerProperty potential = new SimpleIntegerProperty();

        public SkillRow(String name, int real, int potential) {
            this.name.set(name);
            this.real.set(real);
            this.potential.set(potential);
        }

        public StringProperty nameProperty() { return name; }
        public IntegerProperty realProperty() { return real; }
        public IntegerProperty potentialProperty() { return potential; }

        public int getReal() { return real.get(); }
        public void setReal(int v) { real.set(v); }
        public int getPotential() { return potential.get(); }
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