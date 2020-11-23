package com.task.kakaopay.service;

import java.util.Map;

import com.task.kakaopay.domain.DistributionHistory;

public interface DistributionHistoryService {

	/**
	 * service for inserting distributions by token
	 * @param distributionHistory
	 * @throws Exception
	 */
    public void registeDistributions(DistributionHistory distributionHistory) throws Exception;
    /**
     * service for selecting one ds and updating the completed flag
     * @param token
     * @param x_user_id
     * @return MapObject - one distribution history information
     * @throws Exception
     */
    public Map<String,Integer> getOneDsHistory(String token, String x_user_id, String x_room_id) throws Exception;
    /**
     * 
     * @param token
     * @param x_user_id
     * @return String - userIdTaken
     * @throws Exception
     */
    public String getUserIdTaken(String token, String x_user_id) throws Exception;
    
    /**
     * service for initializing AutoIncrement key
     */
    public void initializingAutoIncrement() throws Exception;
}
