package com.zouni.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.util.Calendar;

public class CalendarTypeHandler implements TypeHandler<Calendar> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Calendar parameter, JdbcType jdbcType) throws SQLException {
		if (null != parameter) {
			Timestamp t = new Timestamp(parameter.getTimeInMillis());
			ps.setTimestamp(i, t);
		} else {
			Timestamp t = new Timestamp(Calendar.getInstance().getTimeInMillis());
			ps.setTimestamp(i, t);
		}
	}
	
	@Override
	public Calendar getResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp t = rs.getTimestamp(columnName);
		if (null == t) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		return c;
	}

	@Override
	public Calendar getResult(ResultSet rs, int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public Calendar getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return null;
	}
}
