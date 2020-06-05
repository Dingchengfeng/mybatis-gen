package ${tableinfo.packageName}.domain;

<#list tableinfo.importPack as item>
import ${item};
</#list>
import lombok.Data;

@Data
public class ${tableinfo.tableNameFt?cap_first}DO{

	<#list tableinfo.pkColList as item>
	/**
	 * ${item.comment}
	 */
	private ${item.javaTypeShort} ${item.columName};

	</#list>
	<#list tableinfo.npkColList as item>
	/**
	 * ${item.comment}
	 */
	private ${item.javaTypeShort} ${item.columName};

	</#list>

}