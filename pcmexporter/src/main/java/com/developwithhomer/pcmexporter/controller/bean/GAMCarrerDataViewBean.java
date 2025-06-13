package com.developwithhomer.pcmexporter.controller.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class GAMCarrerDataViewBean implements Serializable {

    private Long uid;

    private String parameterCode;

    private BigDecimal parameterValue;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }

    public BigDecimal getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(BigDecimal parameterValue) {
        this.parameterValue = parameterValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GAMCarrerDataViewBean)) return false;
        GAMCarrerDataViewBean that = (GAMCarrerDataViewBean) o;
        return getUid().equals(that.getUid()) && getParameterCode().equals(that.getParameterCode()) && getParameterValue().equals(that.getParameterValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getParameterCode(), getParameterValue());
    }

    @Override
    public String toString() {
        return "GAMCarrerDataViewBean{" +
                "uid=" + uid +
                ", parameterCode='" + parameterCode + '\'' +
                ", parameterValue=" + parameterValue +
                '}';
    }
}
