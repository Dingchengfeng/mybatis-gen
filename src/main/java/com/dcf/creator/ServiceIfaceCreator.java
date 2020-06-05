package com.dcf.creator;

public class ServiceIfaceCreator extends AbstractCreator {

	@Override
	public JavaBeanParam configBeanParam() {
		AbstractCreator.JavaBeanParam param = new AbstractCreator.JavaBeanParam();
		param.setTemplateName("serviceIface.ftl");
		param.setFileNameSuffix("Mapper");
		param.setPackageSuffix("dao");
		param.setSuffix(".java");
		return param;
	}

}
