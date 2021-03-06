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

import com.task.kakaopay.service.DistributionHistoryService;
import com.task.kakaopay.vo.CommonRequestVO;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/Distributions")
public class DistributionHistoryController {

	@Autowired
	private DistributionHistoryService dsHistoryService;
	
	@RequestMapping(value="", method= RequestMethod.GET)
	public ResponseEntity<Map<String,Integer>> getOneDsHistory(@Validated @RequestHeader("X-USER-ID") String x_user_id,
            @RequestHeader("X-ROOM-ID") String roomId, 
            @RequestBody CommonRequestVO commonRequestVO) throws Exception{ 
		log.info("getOneDsHistory");
		//call service selecting one ds and updating the completed flag
		Map<String,Integer> returnMapObject = dsHistoryService.getOneDsHistory(commonRequestVO.getToken(), x_user_id, roomId);
		return new ResponseEntity<Map<String,Integer>>(returnMapObject, HttpStatus.OK);
	}
}
