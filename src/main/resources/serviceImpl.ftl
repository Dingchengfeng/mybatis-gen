package ${tableinfo.packageName}.dao.impl;

import org.springframework.stereotype.Service;

import ${tableinfo.packageName}.domain.${tableinfo.tableNameFt?cap_first};
import ${tableinfo.packageName}.dao.MyBatisDAO;
import ${tableinfo.packageName}.dao.${tableinfo.tableNameFt?cap_first}DAO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("${tableinfo.tableNameFt}DAO")
public class ${tableinfo.tableNameFt?cap_first}DAOImpl extends SqlSessionDaoSupport implements ${tableinfo.tableNameFt?cap_first}DAO {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insert(${tableinfo.tableNameFt?cap_first} ${tableinfo.tableNameFt}) {
		int count = myBatisDAO.insert("${tableinfo.tableNameFt}.insert${tableinfo.tableNameFt?cap_first}", ${tableinfo.tableNameFt});
		return count > 0;
	}

	@Override
	public boolean update(${tableinfo.tableNameFt?cap_first} ${tableinfo.tableNameFt}) {
		int count = myBatisDAO.update("${tableinfo.tableNameFt}.update${tableinfo.tableNameFt?cap_first}", ${tableinfo.tableNameFt});
		return count > 0;
	}

	@Override
	public boolean updateNullable(${tableinfo.tableNameFt?cap_first} ${tableinfo.tableNameFt}) {
		int count = myBatisDAO.update("${tableinfo.tableNameFt}.update${tableinfo.tableNameFt?cap_first}Nullable", ${tableinfo.tableNameFt});
		return count > 0;
	}

<#if tableinfo.pkColList?size == 1>
	@Override
	public ${tableinfo.tableNameFt?cap_first} selectByPk(${tableinfo.pkColList[0].javaTypeShort} pk) {
		return myBatisDAO.selectForObject("${tableinfo.tableNameFt}.select${tableinfo.tableNameFt?cap_first}ByPk", pk);
	}

	@Override
	public boolean deleteByPk(${tableinfo.pkColList[0].javaTypeShort} pk) {
		int count = myBatisDAO.delete("${tableinfo.tableNameFt}.delete${tableinfo.tableNameFt?cap_first}ByPk", pk);
		return count > 0;
	}

</#if>
}
