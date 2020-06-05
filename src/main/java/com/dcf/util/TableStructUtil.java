package com.dcf.util;

import com.dcf.db.ColumInfo;
import com.dcf.db.TableInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TableStructUtil {

	private static final String[] types = { "TABLE" };

	public static List<String> getTableList() {
		Connection connection = null;
		DatabaseMetaData dbmd = null;
		List<String> result = new ArrayList<String>();
		try {
			JdbcByPropertiesUtil instance = JdbcByPropertiesUtil.getInstance();
			connection = instance.getConnection();
			dbmd = connection.getMetaData();
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			while (rs.next()) {
				result.add(rs.getString("TABLE_NAME"));
			}
			rs.close();
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static TableInfo getConnAndTableStruct(String tableName) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		DatabaseMetaData dbmd = null;
		JdbcByPropertiesUtil instance = JdbcByPropertiesUtil.getInstance();
		connection = instance.getConnection();
		Properties pros = instance.getConfigProperties();
		TableInfo tableinfo = null;
		try {
			dbmd = connection.getMetaData();
			ResultSet rst = dbmd.getPrimaryKeys(null, null, tableName);
			List<String> pkmap = new ArrayList<>();
			while (rst.next()) {
				String key = rst.getString("COLUMN_NAME");
				pkmap.add(key);
			}
			tableinfo = new TableInfo();
			tableinfo.setTableName(tableName);
			tableinfo.setTableNameFt(StringUtil.nameFormat(tableName));
			tableinfo.setPackageName(pros.getProperty("package"));
			List<String> importpack = new ArrayList<String>();
			ResultSet columnSet = dbmd.getColumns(null, "%", tableName, "%");
			while (columnSet.next()) {
				ColumInfo col = new ColumInfo();
				String colname = columnSet.getString("COLUMN_NAME");
				col.setColumName(colname);
				col.setComment(columnSet.getString("REMARKS"));
				String dbtype = columnSet.getString("TYPE_NAME");
				String javaandjdbcType = JdbcByPropertiesUtil.getJavaTypeByDBType(dbtype);
				String[] types = javaandjdbcType.split(",");
				col.setJavaType(types[0]);
				col.setJdbcType(types[1]);
				col.setDbType(dbtype);
				col.setJavaName(StringUtil.nameFormat(colname));
				col.setJavaTypeShort(StringUtil.javaShortTypeName(types[0]));
				if (pkmap.contains(colname)) {
					tableinfo.getPkColList().add(col);
				} else {
					tableinfo.getNpkColList().add(col);
				}
				if (!importpack.contains(types[0]) && !types[0].startsWith("java.lang") /* java.lang开头的不需要import */)
					importpack.add(types[0]);
			}
			tableinfo.setImportPack(importpack);
		} catch (SQLException e) {
			e.printStackTrace();
			instance.close(null, pstmt, connection);
		}
		return tableinfo;
	}

	public static void main(String args[]) {
		// getConnAndTableStruct();
	}
}
