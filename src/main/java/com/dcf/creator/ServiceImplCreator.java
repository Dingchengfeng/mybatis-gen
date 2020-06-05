package com.dcf.creator;

public class ServiceImplCreator extends AbstractCreator {

	@Override
	public JavaBeanParam configBeanParam() {
		AbstractCreator.JavaBeanParam param = new AbstractCreator.JavaBeanParam();
		param.setTemplateName("serviceImpl.ftl");
		param.setFileNameSuffix("DAOImpl");
		param.setPackageSuffix("dao.impl");
		param.setSuffix(".java");
		return param;
	}

}
