package com.task.kakaopay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import com.task.kakaopay.domain.DistributionHistory;
import com.task.kakaopay.domain.Splash;
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
    		dsHistoryService.register(dsHistory);
    	}
    	tokenMap.put("token", token);
    	return tokenMap;
	}
	
	@Override
	public SelectSplashVO getSplashInfo(String token) throws Exception {
		//TODO: 7일 지난건은 조회 불가
		SelectSplashVO returnVO = new SelectSplashVO();
		List<LookUpInfoVO> lookUpInfoVOList = splashMapper.selectAllLookUpInfo(token);
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
		returnVO.setCreatedAt(lookUpInfoVOList.get(0).getCreatedAt());
		returnVO.setCompletedMoney(completedMoney);
		returnVO.setSplashedMoney(lookUpInfoVOList.get(0).getSplashedMoney());
		returnVO.setUserList(userVOList);
		return returnVO;
	}
	
	
}
