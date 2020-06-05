package com.zqwq.common.pojo.commission.domain;

import java.util.Date;
import lombok.Data;

@Data
public class CommissionRuleDO{

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 店铺id
	 */
	private Long storeId;

	/**
	 * 提成类型:commodity,book
	 */
	private String commissionType;

	/**
	 * 提成名称
	 */
	private String commissionName;

	/**
	 * 提成方式,预订次数:bookNum;预订桌数:tableNum;预订人数:personNum;实付百分比:payAmount
	 */
	private String commissionWay;

	/**
	 * 提成量,分或万分之一
	 */
	private Integer commissionAmount;

	/**
	 * 开始日期
	 */
	private String beginDate;

	/**
	 * 结束日期
	 */
	private String endDate;

	/**
	 * 开始时间
	 */
	private String beginTime;

	/**
	 * 解释时间
	 */
	private String endTime;

	/**
	 * 星期
	 */
	private String week;

	/**
	 * 是否删除
	 */
	private String isDelete;

	/**
	 * 创建用户ID
	 */
	private Long creator;

	/**
	 * 更新用户ID
	 */
	private Long updater;

	/**
	 * 创建时间
	 */
	private Date ctime;

	/**
	 * 修改时间
	 */
	private Date utime;


}
