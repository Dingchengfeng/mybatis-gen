package com.dcf.creator;

public class DomainCreator extends AbstractCreator {

	@Override
	public AbstractCreator.JavaBeanParam configBeanParam() {
		AbstractCreator.JavaBeanParam param = new AbstractCreator.JavaBeanParam();
		param.setTemplateName("domain.ftl");
		param.setFileNameSuffix("DO");
		param.setPackageSuffix("domain");
		param.setSuffix(".java");
		return param;
	}

}
