package com.zqwq.common.pojo.commission.dao;

import com.zqwq.common.pojo.commission.CommissionRuleDO;
import java.util.List;

public interface CommissionRuleMapper {

	boolean insert(CommissionRuleDO commissionRule);

	boolean insertSelective(CommissionRuleDO commissionRule);

	boolean updateByPrimaryKeySelective(CommissionRuleDO commissionRule);

	boolean updateNullable(CommissionRuleDO commissionRule);

    List<CommissionRuleDO> listByQuery( CommissionRuleDO query);

	CommissionRuleDO selectByPk(Long pk);

	boolean deleteByPk(Long pk);
}
