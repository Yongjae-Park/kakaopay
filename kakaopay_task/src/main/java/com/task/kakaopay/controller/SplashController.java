package com.task.kakaopay.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.kakaopay.domain.Splash;
import com.task.kakaopay.service.SplashService;
import com.task.kakaopay.util.TokenUtil;
import com.task.kakaopay.vo.CommonRequestVO;
import com.task.kakaopay.vo.SelectSplashVO;
import com.task.kakaopay.vo.SplashRequestVO;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/Splash")
public class SplashController {
	
	@Autowired
    private SplashService splashService;
	
    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Map<String,String>> register(@Validated @RequestHeader("X-USER-ID") String userId,
    		                                          @RequestHeader("X-ROOM-ID") String roomId, 
    		                                          @RequestBody SplashRequestVO splashRequestVO) throws Exception{
    	log.info("register");
    	//call service Inserting Into Splash
    	Splash splashDataObject = new Splash();
    	String token = TokenUtil.generateToken();
    	int splashedMoney = splashRequestVO.getSplashedMoney();
    	int personnel = splashRequestVO.getPersonnel();
    	
    	splashDataObject.setToken(token);
    	splashDataObject.setXUserId(userId);
    	splashDataObject.setXRoomId(roomId);
    	splashDataObject.setSplashedMoney(splashedMoney);
    	splashDataObject.setPersonnel(personnel);
    	
    	Map<String,String> tokenMap = splashService.registeSplashAndDistributions(splashDataObject);
//    	dsHistoryService.initializingAutoIncrement();TODO: 나중에 7일 지난 뿌리기건, 10분 지난 분배건 삭제 로직 추가될 때  as_increment도 필요 현재 요구사항에는 필요없음
    	return new ResponseEntity<>(tokenMap, HttpStatus.OK);
    	
    }
    
    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<SelectSplashVO> getSplashInfo(@Validated @RequestHeader("X-USER-ID") String userId,
            @RequestHeader("X-ROOM-ID") String roomId, 
            @RequestBody CommonRequestVO commonRequestVO) throws Exception{
    	log.info("getSplashInfo");
    	String token = commonRequestVO.getToken();
    	SelectSplashVO returnVO = splashService.getSplashInfo(token, userId);
    	
    	return new ResponseEntity<SelectSplashVO>(returnVO, HttpStatus.OK);
    }
    
	
}
