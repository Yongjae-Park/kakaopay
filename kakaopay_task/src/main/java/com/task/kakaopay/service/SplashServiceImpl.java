package com.task.kakaopay.service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.kakaopay.domain.DistributionHistory;
import com.task.kakaopay.domain.Splash;
import com.task.kakaopay.enums.UserExceptionType;
import com.task.kakaopay.exception.CustomRuntimeException;
import com.task.kakaopay.mapper.SplashMapper;
import com.task.kakaopay.util.DistributionUtil;
import com.task.kakaopay.vo.LookUpInfoVO;
import com.task.kakaopay.vo.SelectSplashVO;
import com.task.kakaopay.vo.UserVO;

@Service
public class SplashServiceImpl implements SplashService {
    
	@Autowired
	private SplashMapper splashMapper;
	@Autowired
	private DistributionHistoryService dsHistoryService;

	@Transactional
	@Override
	public Map<String,String> registeSplashAndDistributions(Splash splash) throws Exception {
		
		Map<String,String> tokenMap = new HashMap<>();
		splashMapper.createSplash(splash);
		int splashedMoney = splash.getSplashedMoney();
    	int personnel = splash.getPersonnel();
    	String token = splash.getToken();
    	String userIdSplashed = splash.getXUserId();
    	String xRoomId = splash.getXRoomId();
    	
		//call service Inserting Into Distribution_history
    	//get devided money array
    	int[] allocatedMoneyList = DistributionUtil.divideSplashedMoney(splashedMoney, personnel);
    	
    	DistributionHistory dsHistory = new DistributionHistory();
    	dsHistory.setToken(token);
    	dsHistory.setUserIdSplashed(userIdSplashed);//뿌리는 유저 id
    	dsHistory.setCompleted(false); //false set in initial setting
    	dsHistory.setXRoomId(xRoomId);
    	
    	for(int i=0;i<personnel;i++) {
    		dsHistory.setAllocatedMoney(allocatedMoneyList[i]);
    		dsHistoryService.registeDistributions(dsHistory);
    	}
    	tokenMap.put("token", token);
    	return tokenMap;
	}
	
	@Override
	public SelectSplashVO getSplashInfo(String token, String xUserId) throws Exception {

		SelectSplashVO returnVO = new SelectSplashVO();
		//create_at만 가져와서 현재시간이랑 비교해보고 지났으면 아예 List<LookUpInfoVO> 가져오지 않고 예외처리
		LocalDateTime initialCreatedTime = selectCreatedAt(token);
		LocalDateTime currentTime = LocalDateTime.now();
		Period period = Period.between(initialCreatedTime.toLocalDate(), currentTime.toLocalDate());
	    if(period.getDays() > 10)
		    throw new CustomRuntimeException(UserExceptionType.HAS_EXPIRED_SPLASH);
		List<LookUpInfoVO> lookUpInfoVOList = splashMapper.selectAllLookUpInfo(token);
		//리스트 가져왔고 조회할수있는 사람인지 먼저 체크
		if(!lookUpInfoVOList.get(0).getUserIdSplashed().equals(xUserId)) {
			throw new CustomRuntimeException(UserExceptionType.ONLY_SPLASHED_USER);
		}
		List<UserVO> userVOList = new ArrayList<>();
		int completedMoney = 0;
		for(LookUpInfoVO vo : lookUpInfoVOList) {
			UserVO tempUserVO = new UserVO();
			tempUserVO.setAllocatedMoney(vo.getAllocatedMoney());
			tempUserVO.setUserIdTaken(vo.getUserIdTaken());
			userVOList.add(tempUserVO);
			if(vo.getIsCompleted()) {
				completedMoney+=vo.getAllocatedMoney();
			}
		}
//		LocalDateTime formattedCreatedAt = lookUpInfoVOList.get(0).getCreatedAt().parse
//				lookUpInfoVOList.get(0).getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		returnVO.setCreatedAt(lookUpInfoVOList.get(0).getCreatedAt());
		returnVO.setCompletedMoney(completedMoney);
		returnVO.setSplashedMoney(lookUpInfoVOList.get(0).getSplashedMoney());
		returnVO.setUserList(userVOList);
		return returnVO;
	}

	@Override
	public LocalDateTime selectCreatedAt(String token) throws Exception {
		LocalDateTime createdAt = splashMapper.selectCreatedAt(token);
		return createdAt;
	}
	
	
}
