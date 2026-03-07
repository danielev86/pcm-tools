package com.redcatdev86.backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class CommonDao {

    protected Integer getNullableInt(ResultSet rs, String col) throws Exception {
        int v = rs.getInt(col);
        return rs.wasNull() ? null : v;
    }

    protected void setNullableInt(PreparedStatement ps, int idx, Integer v) throws Exception {
        if (v == null) ps.setNull(idx, java.sql.Types.INTEGER);
        else ps.setInt(idx, v);
    }

    protected Integer getIntOrNull(ResultSet rs, int idx) throws Exception {
        int v = rs.getInt(idx);
        return rs.wasNull() ? null : v;
    }

    protected void setIntOrNull(PreparedStatement ps, int idx, Integer v) throws Exception {
        if (v == null) ps.setNull(idx, Types.INTEGER);
        else ps.setInt(idx, v);
    }

    protected Double getNullableDouble(ResultSet rs, String col) throws Exception {
        double v = rs.getDouble(col);
        return rs.wasNull() ? null : v;
    }

    protected void setNullableDouble(PreparedStatement ps, int idx, Double v) throws Exception {
        if (v == null) ps.setNull(idx, java.sql.Types.REAL);
        else ps.setDouble(idx, v);
    }


}
