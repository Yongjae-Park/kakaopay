package com.task.kakaopay.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.kakaopay.domain.DistributionHistory;
import com.task.kakaopay.mapper.DistributionHistoryMapper;
import com.task.kakaopay.vo.GetDsVO;

@Service
public class DistributionHistoryServiceImpl implements DistributionHistoryService{

	@Autowired
	private DistributionHistoryMapper mapper;
	
	@Override
	public void register(DistributionHistory distributionHistory) throws Exception {
		mapper.createDsHistory(distributionHistory);
	}

	@Override
	public GetDsVO getOneDsHistory(String token, String x_user_id) throws Exception {
		return mapper.getOneDsHistory(token, x_user_id);
	}

	@Override
	public void updateisCompleted(int distributionNo, String userIdTaken) throws Exception {
		mapper.updateisCompleted(distributionNo, userIdTaken);
	}
	
	
}
