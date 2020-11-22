package com.task.kakaopay.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import com.task.kakaopay.domain.SplashRequestDto;
import com.task.kakaopay.service.DistributionHistoryService;
import com.task.kakaopay.service.SplashService;
import com.task.kakaopay.vo.GetDsVO;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/receive")
public class DistributionHistoryController {

	@Autowired
	private DistributionHistoryService dsHistoryService;
	@Autowired
	private SplashService splashService;
	/**
	 * 
	 * @param splashedMoney - total amount of splashed money
	 * @param personnel - total amount of person in the room
	 * @return distributionList - JSONArray of well distributed moneyObject(sorted order by 작은순) : 젤 늦게 받은 사람이 젤 큰돈 받는 눈치게임
	 */

	
	@RequestMapping(value="", method= RequestMethod.GET)
	public ResponseEntity<JSONObject> getOneDsHistory(@Validated @RequestHeader("X-USER-ID") String x_user_id,
            @RequestHeader("X-ROOM-ID") String roomId, 
            @RequestBody CommonRequestDto commonRequestDto) throws Exception{ 
		
		GetDsVO splashedMoneyObject = dsHistoryService.getOneDsHistory(commonRequestDto.getToken(), x_user_id);
		JSONObject returnJson = new JSONObject();
		
		if(splashedMoneyObject.getUserIdSplashed().equals(x_user_id)) {
			//x_user_id == user_id_splashed
			returnJson.put("errorCode" , "002");
			returnJson.put("erroMsg", "본인이 뿌린 돈은 받을 수 없습니다.");
		}
		else if(splashedMoneyObject.equals(null)) {
			//all distributions is completed || TODO: the time is over
			returnJson.put("errorCode" , "003");
			returnJson.put("erroMsg","");
		}
		else {
			//success case
			//there are one or more distribution SPLASH about the token
			//and request user(x_user_id) is not same with user_id_splashed
			returnJson.put("winMoney",splashedMoneyObject.getAllocatedMoney());
		}
		//리턴하기전에 TODO: 뿌리기건에 대해서 남은금액 update, 받은 건에 대해서 is_completed = 'true'업데이트
		//1. splashtable업데이트
		//TODO: 뿌리기건에 대해서 token으로 조회해오고 현재금액에서 지금 가져가는금액 뺴야돼 내일여기부터 다시
//		try {
//		}
//		catch()
		//2.
		dsHistoryService.updateisCompleted(splashedMoneyObject.getDistributionNo(), x_user_id);
		return new ResponseEntity<JSONObject>(returnJson, HttpStatus.OK);
	}
}
