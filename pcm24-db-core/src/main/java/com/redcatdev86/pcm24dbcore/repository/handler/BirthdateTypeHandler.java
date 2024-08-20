package com.redcatdev86.pcm24dbcore.repository.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@MappedJdbcTypes(value = JdbcType.DATE, includeNullJdbcType = true)
public class BirthdateTypeHandler extends BaseTypeHandler<Date> {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public BirthdateTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getDate(rs.getString(columnName));
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getDate(rs.getString(columnIndex));
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getDate(cs.getString(columnIndex));
    }

    private Date getDate(String sqlTimestamp) {
        Date date = null;
        if (sqlTimestamp != null) {
            try{
                date = sdf.parse(sqlTimestamp);
            }catch(ParseException e){
                log.error("Error print date: {}", e.getMessage(), e);
            }
        }
        return date;
    }
}
