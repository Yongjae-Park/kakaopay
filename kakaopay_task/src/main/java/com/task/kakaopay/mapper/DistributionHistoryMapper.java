package com.task.kakaopay.mapper;

import com.task.kakaopay.domain.DistributionHistory;
import com.task.kakaopay.vo.GetDsVO;

public interface DistributionHistoryMapper {
	 public void createDsHistory(DistributionHistory distributionHistory) throws Exception;
	 public GetDsVO getOneDsHistory(String token, String x_user_id) throws Exception;
	 public void updateisCompleted(int distributionNo, String userIdTaken) throws Exception;
	 public void initializingAsIncrement() throws Exception;
}
