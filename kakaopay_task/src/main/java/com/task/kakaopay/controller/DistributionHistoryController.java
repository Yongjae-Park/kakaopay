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

import com.task.kakaopay.service.DistributionHistoryService;
import com.task.kakaopay.service.SplashService;
import com.task.kakaopay.vo.CommonRequestVO;
import com.task.kakaopay.vo.GetDsVO;
import com.task.kakaopay.vo.SplashRequestVO;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/Distributions")
public class DistributionHistoryController {

	@Autowired
	private DistributionHistoryService dsHistoryService;
	@Autowired
	private SplashService splashService;
	
	@RequestMapping(value="", method= RequestMethod.GET)
	public ResponseEntity<JSONObject> getOneDsHistory(@Validated @RequestHeader("X-USER-ID") String x_user_id,
            @RequestHeader("X-ROOM-ID") String roomId, 
            @RequestBody CommonRequestVO commonRequestVO) throws Exception{ 
		log.info("getOneDsHistory");
		//call service selecting one ds and updating the completed flag
		JSONObject returnJsonObject = dsHistoryService.getOneDsHistory(commonRequestVO.getToken(), x_user_id);
		return new ResponseEntity<JSONObject>(returnJsonObject, HttpStatus.OK);
	}
}
