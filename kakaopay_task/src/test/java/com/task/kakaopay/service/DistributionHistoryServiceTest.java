package com.task.kakaopay.service;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.task.kakaopay.util.TokenUtil;
import com.task.kakaopay.vo.SelectSplashVO;

import lombok.extern.java.Log;
@Log
@SpringBootTest
public class DistributionHistoryServiceTest {

	@Autowired
	private DistributionHistoryService dsHistoryService;
	
	
	@Test
	public void selectOndDsHistoryServiceTest() throws Exception {
		log.info("selectOndDsHistoryServiceTest");
		// call getOneDsHistory once TEST
		//success case
//		Map<String,Integer> returnMapObject_case1 = dsHistoryService.getOneDsHistory("Inj", "muzy" , "room1");
//		Gson gson = new Gson();
//		System.out.println("returnMapObject : " + gson.toJson(returnMapObject_case1, Map.class));
		
		//exception case
		//ANYONE_BUT_NOT_YOURSELF
//		Map<String,Integer> returnMapObject_case2 = dsHistoryService.getOneDsHistory("Inj", "muzy1" , "room1");
		//exception case
		//WRONG_ROOM_ACCESS
//		Map<String,Integer> returnMapObject_case3 = dsHistoryService.getOneDsHistory("Inj", "muzy1" , "room10");
		//exception case
		//DUPLICATED_USER
//		Map<String,Integer> returnMapObject_case4 = dsHistoryService.getOneDsHistory("Inj", "muzy" , "room10");
		//...10minutes later
		//exception case
		//HAS_EXPIRED_SPLASH 
		Map<String,Integer> returnMapObject_case5 = dsHistoryService.getOneDsHistory("Inj", "muzy" , "room10");
	}
	
}
