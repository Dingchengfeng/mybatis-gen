package com.dcf.creator;

import com.dcf.db.TableInfo;
import com.dcf.util.FileIOUtil;
import com.dcf.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCreator {
	public Map<String, Object> params = new HashMap<>();

	public void createJavaBean(TableInfo tableinfo) {
		params.put("tableinfo", tableinfo);
		JavaBeanParam param = configBeanParam();
		createJavaBean(param.getTemplateName(), param.getSuffix(), param.getFileNameSuffix(),
				param.isUseCamelCaseForFileName(), param.getPackageSuffix());
	}

	private void createJavaBean(String templateName, String suffix, String fileNameSuffix,
			boolean useCamelCaseForFileName, String packageSuffix) {
		TableInfo tableinfo = (TableInfo) params.get("tableinfo");
		String template = FileIOUtil.readTemplate(templateName);
		String tmp = StringUtil.generateMessageWithTemplate(params, template);
		FileIOUtil.createFile(
				(useCamelCaseForFileName ? StringUtil.firstCharUpcase(tableinfo.getTableNameFt()) : tableinfo.getTableName()) + fileNameSuffix + suffix,
				packageSuffix, tmp);
	}

	public abstract AbstractCreator.JavaBeanParam configBeanParam();

	public static class JavaBeanParam {

		private String templateName;

		private String suffix = ".java";

		private String fileNameSuffix = "";

		// 默认使用CamelCase的文件命名
		private boolean useCamelCaseForFileName = true;

		private String packageSuffix = "";

		public String getTemplateName() {
			return templateName;
		}

		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		public String getFileNameSuffix() {
			return fileNameSuffix;
		}

		public void setFileNameSuffix(String javaBeanSuffix) {
			this.fileNameSuffix = javaBeanSuffix;
		}

		public boolean isUseCamelCaseForFileName() {
			return useCamelCaseForFileName;
		}

		public void setUseCamelCaseForFileName(boolean useCamelCaseForFileName) {
			this.useCamelCaseForFileName = useCamelCaseForFileName;
		}

		public String getPackageSuffix() {
			return packageSuffix;
		}

		public void setPackageSuffix(String packageSuffix) {
			this.packageSuffix = packageSuffix;
		}
	}
}
