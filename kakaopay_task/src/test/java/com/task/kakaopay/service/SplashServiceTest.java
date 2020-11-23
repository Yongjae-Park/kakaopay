package com.task.kakaopay.service;

import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.client.MockRestServiceServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.task.kakaopay.domain.Splash;
import com.task.kakaopay.util.TokenUtil;
import com.task.kakaopay.vo.SelectSplashVO;

import lombok.extern.java.Log;
@Log
@SpringBootTest
public class SplashServiceTest {

	@Autowired
	private SplashService splashService;
	
	public String token;
	@Test
	public void registeSplashTest() throws Exception {
		log.info("splash regist TEST");
		
		// call registeSplashAndDistributions TEST
		String userId = "rion";
		String roomId = "room10";
		token = TokenUtil.generateToken();
		int splashedMoney = 1000000;
		int personnel = 1000; 
	    Splash splashDataObject = new Splash();
	    splashDataObject.setToken(token);
    	splashDataObject.setXUserId(userId);
    	splashDataObject.setXRoomId(roomId);
    	splashDataObject.setSplashedMoney(splashedMoney);
    	splashDataObject.setPersonnel(personnel);
    	
    	Map<String,String> tokenMap = splashService.registeSplashAndDistributions(splashDataObject);
    	
    	System.out.println("tokenMap :" + tokenMap);
    	
	}
	
	@Test
	public void getSplashInfo() throws Exception{
	    //call registeSplashAndDistributions TEST
		//success case
		String token = "$5r";
		String userId = "rion";
		SelectSplashVO returnVO_case1 = new SelectSplashVO();
		returnVO_case1 = splashService.getSplashInfo(token, userId);
		Gson gson = new Gson();
		System.out.println("returnVO : " + gson.toJson(returnVO_case1, SelectSplashVO.class));
		
		//exceptioncase
		//ONLY_SPLASHED_USER
//		SelectSplashVO returnVO_case2 = splashService.getSplashInfo(token, "rion2");
		
		//exceptioncase
		//HAS_EXPIRED_FOR_LOOK_UP
		//*SplashServiceImple 내 testLocalTime으로 test진행
		//for testCase..
        //LocalDateTime testLocalTime = LocalDateTime.parse("2020-12-25T10:15:30");
        //long days = ChronoUnit.DAYS.between(initialCreatedTime.toLocalDate(),testLocalTime.toLocalDate());
		SelectSplashVO returnVO_case3 = splashService.getSplashInfo(token, "rion3");
		
		
		
	}
}
