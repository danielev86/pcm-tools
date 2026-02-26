package com.redcatdev86.ui.model;

import com.redcatdev86.backend.model.GamCareerData;
import javafx.beans.property.*;

import java.io.Serializable;

public class GamCareerDataBean implements Serializable {

    private final IntegerProperty uid = new SimpleIntegerProperty();
    private final StringProperty constant = new SimpleStringProperty();
    private final DoubleProperty value = new SimpleDoubleProperty();

    // per dirty tracking / undo
    private final DoubleProperty originalValue = new SimpleDoubleProperty();
    private final BooleanProperty dirty = new SimpleBooleanProperty(false);

    public GamCareerDataBean() {}

    public GamCareerDataBean(int uid, String constant, double value) {
        setUid(uid);
        setConstant(constant);
        setValue(value);
        setOriginalValue(value);
        setDirty(false);

        // se cambia value, diventa dirty se diverso dall’originale
        this.value.addListener((obs, ov, nv) -> setDirty(Double.compare(getValue(), getOriginalValue()) != 0));
    }

    public int getUid() { return uid.get(); }
    public void setUid(int v) { uid.set(v); }
    public IntegerProperty uidProperty() { return uid; }

    public String getConstant() { return constant.get(); }
    public void setConstant(String v) { constant.set(v); }
    public StringProperty constantProperty() { return constant; }

    public double getValue() { return value.get(); }
    public void setValue(double v) { value.set(v); }
    public DoubleProperty valueProperty() { return value; }

    public double getOriginalValue() { return originalValue.get(); }
    public void setOriginalValue(double v) { originalValue.set(v); }
    public DoubleProperty originalValueProperty() { return originalValue; }

    public boolean isDirty() { return dirty.get(); }
    public void setDirty(boolean v) { dirty.set(v); }
    public BooleanProperty dirtyProperty() { return dirty; }

    public void markClean() {
        setOriginalValue(getValue());
        setDirty(false);
    }

    public void undo() {
        setValue(getOriginalValue());
        setDirty(false);
    }

    // --- converter “come prima” ---
    public static GamCareerDataBean fromModel(GamCareerData m) {
        return m == null ? null : new GamCareerDataBean(m.getUid(), m.getConstant(), m.getValue());
    }

    public static GamCareerData toModel(GamCareerDataBean b) {
        return b == null ? null : new GamCareerData(b.getUid(), b.getConstant(), b.getValue());
    }

}
