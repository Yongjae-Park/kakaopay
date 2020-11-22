package com.task.kakaopay.controller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.kakaopay.domain.CommonRequestDto;
import com.task.kakaopay.domain.DistributionHistory;
import com.task.kakaopay.domain.Splash;
import com.task.kakaopay.domain.SplashRequestDto;
import com.task.kakaopay.service.DistributionHistoryService;
import com.task.kakaopay.service.SplashService;
import com.task.kakaopay.util.DistributionUtil;
import com.task.kakaopay.util.TokenUtil;
import com.task.kakaopay.vo.SelectSplashVO;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/Splash")
public class SplashController {
	
	@Autowired
    private SplashService splashService;
	@Autowired
	private DistributionHistoryService dsHistoryService;
	
    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<String> register(@Validated @RequestHeader("X-USER-ID") String userId,
    		                                          @RequestHeader("X-ROOM-ID") String roomId, 
    		                                          @RequestBody SplashRequestDto splashRequestDto) throws Exception{
    	log.info("register");
    	//call service Inserting Into Splash
    	Splash splashDataObject = new Splash();
    	String token = TokenUtil.generateToken("abe"); //TODO: 토큰 고유값 생성
    	
    	int splashedMoney = splashRequestDto.getSplashedMoney();
    	int personnel = splashRequestDto.getPersonnel();
    	
    	splashDataObject.setToken(token);
    	splashDataObject.setX_user_id(userId);
    	splashDataObject.setX_room_id(roomId);
    	splashDataObject.setSplashedMoney(splashedMoney);
    	splashDataObject.setPersonnel(personnel);
    	
    	splashService.registeSplash(splashDataObject);
    	
    	//call service Inserting Into Distribution_history
    	List<Integer> allocatedMoneyList = DistributionUtil.divideSplashedMoney(splashedMoney, personnel);
    	
    	DistributionHistory dsHistory = new DistributionHistory();
    	dsHistory.setToken(token);
    	dsHistory.setUserIdSplashed(userId);//뿌리는 유저 id
    	dsHistory.setCompleted(false); //false set in initial setting
    	
    	for(int i=0;i<personnel;i++) {
    		dsHistory.setAllocatedMoney(allocatedMoneyList.get(i));
    		dsHistoryService.register(dsHistory);
    	}
    	
    	return new ResponseEntity<>("Success", HttpStatus.OK);
    	
    }
    
    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<SelectSplashVO> getSplashInfo(@Validated @RequestHeader("X-USER-ID") String userId,
            @RequestHeader("X-ROOM-ID") String roomId, 
            @RequestBody CommonRequestDto commonRequestDto) throws Exception{
				
    	String token = commonRequestDto.getToken();
    	
    	SelectSplashVO returnVO = splashService.getSplashInfo(token);
    	
    	
    	
    	
    	
    	return new ResponseEntity<SelectSplashVO>(returnVO, HttpStatus.OK);
    	
    	
    }
    
	
}
