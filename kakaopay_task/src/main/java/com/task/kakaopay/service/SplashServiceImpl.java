package com.task.kakaopay.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.kakaopay.domain.Splash;
import com.task.kakaopay.mapper.SplashMapper;
import com.task.kakaopay.vo.LookUpInfoVO;
import com.task.kakaopay.vo.SelectSplashVO;
import com.task.kakaopay.vo.UserVO;

@Service
public class SplashServiceImpl implements SplashService {
    
	@Autowired
	private SplashMapper mapper;

	@Override
	public void registeSplash(Splash splash) throws Exception {
		mapper.createSplash(splash);
	}
	@Override
	public SelectSplashVO getSplashInfo(String token) throws Exception {
		
		SelectSplashVO returnVO = new SelectSplashVO();
		List<LookUpInfoVO> lookUpInfoVOList = mapper.selectAllLookUpInfo(token);
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
