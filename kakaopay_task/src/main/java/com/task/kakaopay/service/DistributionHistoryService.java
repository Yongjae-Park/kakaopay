package com.task.kakaopay.service;

import org.json.simple.JSONObject;

import com.task.kakaopay.domain.DistributionHistory;

public interface DistributionHistoryService {

	/**
	 * service for inserting distributions by token
	 * @param distributionHistory
	 * @throws Exception
	 */
    public void register(DistributionHistory distributionHistory) throws Exception;
    /**
     * service for selecting one ds and updating the completed flag
     * @param token
     * @param x_user_id
     * @return
     * @throws Exception
     */
    public JSONObject getOneDsHistory(String token, String x_user_id, String x_room_id) throws Exception;
    
    public String getUserIdTaken(String token, String x_user_id) throws Exception;
    
    /**
     * service for initializing AutoIncrement key
     */
    public void initializingAutoIncrement() throws Exception;
}
