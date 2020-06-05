package com.dcf.creator;

public class MapperCreator extends AbstractCreator {

	@Override
	public AbstractCreator.JavaBeanParam configBeanParam() {
		AbstractCreator.JavaBeanParam param = new AbstractCreator.JavaBeanParam();
		param.setTemplateName("mapper.ftl");
		param.setFileNameSuffix("Mapper");
		param.setUseCamelCaseForFileName(true);
		param.setPackageSuffix("");
		param.setSuffix(".xml");
		return param;
	}

}
