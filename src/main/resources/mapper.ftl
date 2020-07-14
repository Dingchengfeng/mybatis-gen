<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${tableinfo.packageName}.dao.mapper.${tableinfo.tableNameFt?cap_first}Mapper">

	<sql id="insertColumns">
        <![CDATA[
		<#if !tableinfo.shouldUseGeneratedKeys><#list tableinfo.pkColList as item>${item.columName}<#if item_has_next>, </#if></#list>, </#if><#list tableinfo.npkColList as item>${item.columName}<#if item_has_next>, </#if></#list>
		]]>
	</sql>

	<sql id="columns">
		id,<include refid="insertColumns"/>
	</sql>

	<insert id="insertBatch" <#if tableinfo.shouldUseGeneratedKeys> useGeneratedKeys="true" keyProperty="id"</#if> >
		INSERT INTO
		${tableinfo.tableName?lower_case} (<include refid="insertColumns"/>)
		VALUES
        <foreach collection ="list" item="item" separator =",">
		(
			<#if !tableinfo.shouldUseGeneratedKeys>
				<#list tableinfo.pkColList as item>
			${r"#"}{item.${item.columName}},
				</#list>
			</#if>
			<#list tableinfo.npkColList as item>
			${r"#"}{item.${item.columName}}<#if item_has_next>,</#if>
			</#list>
		)
        </foreach >
	</insert>

    <insert id="insert" <#if tableinfo.shouldUseGeneratedKeys> useGeneratedKeys="true" keyProperty="id"</#if> >
        INSERT INTO
		${tableinfo.tableName?lower_case} (<include refid="insertColumns"/>)
        VALUES
        (
			<#if !tableinfo.shouldUseGeneratedKeys>
				<#list tableinfo.pkColList as item>
					${r"#"}{${item.columName}},
				</#list>
			</#if>
			<#list tableinfo.npkColList as item>
				${r"#"}{${item.columName}}<#if item_has_next>,</#if>
			</#list>
        )
    </insert>

	<update id="updateByPrimaryKeySelective">
		UPDATE ${tableinfo.tableName?lower_case}
		<set>
			<#list tableinfo.npkColList as item>
			<if test="${item.columName} != null">
				${item.columName} = ${r"#"}{${item.columName}}<#if item_has_next>,</#if>
			</if>
			</#list>
		</set>
		WHERE <#list tableinfo.pkColList as item>${item.columName} = ${r"#"}{${item.columName}}<#if item_has_next> and </#if></#list>
	</update>

	<update id="updateNullable">
		UPDATE ${tableinfo.tableName?lower_case}
		<set>
			<#list tableinfo.npkColList as item>
			${item.columName} = ${r"#"}{${item.columName}}<#if item_has_next>,</#if>
			</#list>
		</set>
		WHERE <#list tableinfo.pkColList as item>${item.columName} = ${r"#"}{${item.columName}}<#if item_has_next> and </#if></#list>
	</update>

	<select id="listByQuery" resultType="${tableinfo.packageName}.${tableinfo.tableNameFt?cap_first}DO">
		SELECT <include refid="columns"/>
		FROM ${tableinfo.tableName?lower_case}
        <where>
		<#list tableinfo.npkColList as item>
            <if test="${item.columName} != null">
			and ${item.columName} = ${r"#"}{${item.columName}}<#if item_has_next></#if>
            </if>
		</#list>
        </where>
	</select>

<#if tableinfo.pkColList?size == 1>
	<select id="selectByPk" resultType="${tableinfo.packageName}.${tableinfo.tableNameFt?cap_first}DO">
		SELECT <include refid="columns"/>
		FROM ${tableinfo.tableName?lower_case}
		where <#list tableinfo.pkColList as item>${item.columName} = ${r"#"}{${item.columName}}<#if item_has_next> and </#if></#list>
	</select>

	<delete id="deleteByPk">
		delete from ${tableinfo.tableName?lower_case} where <#list tableinfo.pkColList as item>${item.columName} = ${r"#"}{${item.columName}}<#if item_has_next> and </#if></#list>
	</delete>
</#if>
</mapper>
