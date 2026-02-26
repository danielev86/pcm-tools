package com.redcatdev86.backend.model;

import java.io.Serializable;

public class GamCareerData implements Serializable {

    private final int uid;
    private final String constant;
    private final double value;

    public GamCareerData(int uid, String constant, double value) {
        this.uid = uid;
        this.constant = constant;
        this.value = value;
    }

    public int getUid() { return uid; }
    public String getConstant() { return constant; }
    public double getValue() { return value; }

}
