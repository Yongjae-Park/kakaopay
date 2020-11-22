package com.task.kakaopay.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@Override
	public JSONObject getOneDsHistory(String token, String x_user_id) throws Exception {
		//select one DistributionHistory
		GetDsVO distributionVO = mapper.getOneDsHistory(token, x_user_id);
		//completion setting for have been received
		//
		mapper.updateisCompleted(distributionVO.getDistributionNo(), x_user_id);
        JSONObject returnJson = new JSONObject();
		
		if(distributionVO.getUserIdSplashed().equals(x_user_id)) {
			//x_user_id == user_id_splashed
			returnJson.put("errorCode" , "002");
			returnJson.put("erroMsg", "본인이 뿌린 돈은 받을 수 없습니다.");
		}
		else if(distributionVO.equals(null)) {
			//all distributions is completed || TODO: the time is over
			returnJson.put("errorCode" , "003");
			returnJson.put("erroMsg","");
		}
		else {
			//success case
			//there are one or more distribution SPLASH about the token
			//and request user(x_user_id) is not same with user_id_splashed
			returnJson.put("winMoney",distributionVO.getAllocatedMoney());
		}
		
		return returnJson;
	}

	@Override
	public void initializingAutoIncrement() throws Exception {
		mapper.initializingAsIncrement();
	}
	
}
