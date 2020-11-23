package com.task.kakaopay.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.task.kakaopay.domain.DistributionHistory;
import com.task.kakaopay.enums.UserExceptionType;
import com.task.kakaopay.exception.CustomRuntimeException;
import com.task.kakaopay.mapper.DistributionHistoryMapper;
import com.task.kakaopay.vo.GetDsVO;

@Service
public class DistributionHistoryServiceImpl implements DistributionHistoryService{

	@Autowired
	private DistributionHistoryMapper distributionMapper;
	
	@Override
	public void registeDistributions(DistributionHistory distributionHistory) throws Exception {
		distributionMapper.createDsHistory(distributionHistory);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public Map<String,Integer> getOneDsHistory(String token, String x_user_id, String x_room_id) throws Exception {
		//check user exception case before select 
		//exception occur 1. when request user == userIdTaken
		String userIdTaken = getUserIdTaken(token, x_user_id);
    	if(userIdTaken != null && userIdTaken.equals(x_user_id)) {
			throw new CustomRuntimeException(UserExceptionType.DUPLICATED_USER);
		}
		//select one DistributionHistory
		GetDsVO distributionVO = distributionMapper.getOneDsHistory(token, x_user_id);
		//exception occur 3. does not match room id
		if(!distributionVO.getxRoomId().equals(x_room_id)) {
			throw new CustomRuntimeException(UserExceptionType.WRONG_ROOM_ACCESS);
		}
		//exception occur 2. when request user == userSplashed
		if(distributionVO.getUserIdSplashed().equals(x_user_id)) {
			throw new CustomRuntimeException(UserExceptionType.ANYONE_BUT_NOT_YOURSELF);
		}
		//exception occur 4. expired splash
		LocalDateTime currentTime = LocalDateTime.now();
		Duration duration = Duration.between(distributionVO.getExpiredAt(), currentTime);
		if(duration.toSeconds() > 600)
		    throw new CustomRuntimeException(UserExceptionType.HAS_EXPIRED_SPLASH);
		//completion setting for have been received
		distributionMapper.updateisCompleted(distributionVO.getDistributionNo(), x_user_id);
        
		Map<String,Integer> returnMap = new HashMap<>();
		returnMap.put("winMoney", distributionVO.getAllocatedMoney());
		
		return returnMap;
	}

	@Override
	public void initializingAutoIncrement() throws Exception {
		distributionMapper.initializingAsIncrement();
	}

	@Override
	public String getUserIdTaken(String token, String x_user_id) throws Exception {
		String userIdTaken = distributionMapper.getUserIdTaken(token, x_user_id);
		return userIdTaken;
	}
	
}
