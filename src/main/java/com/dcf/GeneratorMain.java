package com.dcf;

import com.dcf.creator.*;
import com.dcf.db.TableInfo;
import com.dcf.util.FileIOUtil;
import com.dcf.util.JdbcByPropertiesUtil;
import com.dcf.util.StringUtil;
import com.dcf.util.TableStructUtil;
import com.mysql.jdbc.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GeneratorMain {

	public static void main(String args[]) throws InstantiationException, IllegalAccessException {
		List<TableInfo> tableInfos = new ArrayList<>();
		String tablename = (String) JdbcByPropertiesUtil.getInstance().getConfigProperties().get("tableName");
		//为空就全表生成
		if (StringUtils.isNullOrEmpty(tablename)) {
			List<String> tables = TableStructUtil.getTableList();
			for (String table : tables) {
				tableInfos.add(generateFiles(table));
			}
		} else {
			List<String> tables = StringUtils.split(tablename, ",", true);
			for (String table : tables) {
				if (!StringUtils.isNullOrEmpty(table)) {
					tableInfos.add(generateFiles(table));
				}
			}
		}

		System.out.println("generating mapper list file...");
		try {
			Writer writer = FileIOUtil.getWriter("mapper_list.txt", "");
			for (TableInfo tableInfo : tableInfos) {
				String className = StringUtil.firstCharUpcase(tableInfo.getTableNameFt());
				writer.write("<typeAlias type=\"" + tableInfo.getPackageName() + ".domain." + className + "\" alias=\"" + className + "\" />\n");
			}
			writer.write("\n");
			for (TableInfo tableInfo : tableInfos) {
				writer.write("<mapper resource=\"mapper/" + StringUtil.nameFormatOther(tableInfo.getTableName().toLowerCase()) + "Mapper.xml\" />\n");
			}
			// done
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("end...");
	}

	@SuppressWarnings("rawtypes")
	public static TableInfo generateFiles(String tablename) {
		TableInfo tableinfo = TableStructUtil.getConnAndTableStruct(tablename);
		Class[] clzzs = {
			MapperCreator.class,
			DomainCreator.class,
			ServiceIfaceCreator.class,
			//ServiceImplCreator.class
		};
		for (Class clzz : clzzs) {
			AbstractCreator exc;
			try {
				exc = (AbstractCreator) clzz.newInstance();
				exc.createJavaBean(tableinfo);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return tableinfo;
	}
}
