package ${tableinfo.packageName}.dao;

import ${tableinfo.packageName}.${tableinfo.tableNameFt?cap_first}DO;
import java.util.List;

public interface ${tableinfo.tableNameFt?cap_first}Mapper {

	boolean insert(${tableinfo.tableNameFt?cap_first}DO ${tableinfo.tableNameFt});

	boolean insertSelective(${tableinfo.tableNameFt?cap_first}DO ${tableinfo.tableNameFt});

	boolean updateByPrimaryKeySelective(${tableinfo.tableNameFt?cap_first}DO ${tableinfo.tableNameFt});

	boolean updateNullable(${tableinfo.tableNameFt?cap_first}DO ${tableinfo.tableNameFt});

    List<${tableinfo.tableNameFt?cap_first}DO> listByQuery( ${tableinfo.tableNameFt?cap_first}DO query);

<#if tableinfo.pkColList?size == 1>
	${tableinfo.tableNameFt?cap_first}DO selectByPk(${tableinfo.pkColList[0].javaTypeShort} pk);

	boolean deleteByPk(${tableinfo.pkColList[0].javaTypeShort} pk);
</#if>
}
