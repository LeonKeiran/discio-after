package com.zeeyeh.discio.system.data.type.handlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Leon_Keiran
 * @description varchar转long类型处理器
 * @date 2024/12/28/周六 19:28:59
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
public class VarcharToLongTypeHandler extends BaseTypeHandler<Long> {

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            Instant instant = Instant.ofEpochMilli(parameter);
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE_ID);
            ps.setString(i, localDateTime.toString());
        }
    }

    @Override
    public Long getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String timeString = rs.getString(columnName);
        if (timeString != null && !timeString.isEmpty()) {
            LocalDateTime localDateTime = LocalDateTime.parse(timeString);
            return localDateTime.atZone(ZONE_ID).toInstant().toEpochMilli();
        }
        return null;
    }

    @Override
    public Long getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String timeString = rs.getString(columnIndex);
        if (timeString != null && !timeString.isEmpty()) {
            LocalDateTime localDateTime = LocalDateTime.parse(timeString);
            return localDateTime.atZone(ZONE_ID).toInstant().toEpochMilli();
        }
        return null;
    }

    @Override
    public Long getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String timeString = cs.getString(columnIndex);
        if (timeString != null && !timeString.isEmpty()) {
            LocalDateTime localDateTime = LocalDateTime.parse(timeString);
            return localDateTime.atZone(ZONE_ID).toInstant().toEpochMilli();
        }
        return null;
    }
}
