package com.task.kakaopay.service;

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
    	//call registeSplashAndDistributions manytimes
//    	List<String> userIdList = new ArrayList<>();
//    	List<String> roomIdList = new ArrayList<>();
//    	List<String> tokenList = new ArrayList<>();
//    	List<Splash> splashDataObjectList = new ArrayList<>();
    	
	}
	
	@Test
	public void getSplashInfo() throws Exception{
	// call registeSplashAndDistributions TEST
		String token = "$5r";
		String userId = "rion";
		SelectSplashVO returnVO = splashService.getSplashInfo(token, userId);
		Gson gson = new Gson();
		System.out.println("returnVO : " + gson.toJson(returnVO, SelectSplashVO.class));
	}
}
