package com.dcf.db;

import java.util.ArrayList;
import java.util.List;

public class TableInfo {

	public static final String GENERATED_KEY_NAME = "ID";
	
	private String tableName; // 表名

	private String tableNameFt; // 格式化后表名

	private List<String> importPack = new ArrayList<String>(); // import数据类型

	private String packageName; // 包名

	private List<ColumInfo> pkColList = new ArrayList<ColumInfo>(); // 主键

	private List<ColumInfo> npkColList = new ArrayList<ColumInfo>(); // 非主键

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableNameFt() {
		return tableNameFt;
	}

	public void setTableNameFt(String tableNameFt) {
		this.tableNameFt = tableNameFt;
	}

	public List<String> getImportPack() {
		return importPack;
	}

	public void setImportPack(List<String> importPack) {
		this.importPack = importPack;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<ColumInfo> getPkColList() {
		return pkColList;
	}

	public void setPkColList(List<ColumInfo> pkColList) {
		this.pkColList = pkColList;
	}

	public List<ColumInfo> getNpkColList() {
		return npkColList;
	}

	public void setNpkColList(List<ColumInfo> npkColList) {
		this.npkColList = npkColList;
	}

	public boolean getShouldUseGeneratedKeys() {
		return pkColList.size() == 1 && GENERATED_KEY_NAME.equalsIgnoreCase(pkColList.get(0).getColumName());
	}
}
