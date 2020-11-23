package com.task.kakaopay.service;

import java.util.Map;

import com.task.kakaopay.domain.Splash;
import java.time.LocalDateTime;
import com.task.kakaopay.vo.SelectSplashVO;

public interface SplashService {
	/**
	 * service for registe a splash and divided distributions by splash
	 * @param splash
	 * @return Ojbect token value
	 * @throws Exception
	 */
    public Map<String,String> registeSplashAndDistributions(Splash splash) throws Exception;
    /**
     * service for get Information about a splash
     * @param token
     * @return SelecetSplashVO - include createdAt, splashedMoney amount, total completedMoney, userList
     * @throws Exception
     */
    public SelectSplashVO getSplashInfo(String token, String xUserId) throws Exception;
    
    /**
     * service for select created time for compare with current time
     * @param token
     * @return LocalDatetime - when splash created at
     * @throws Exception
     */
    public LocalDateTime selectCreatedAt(String token)throws Exception;
    
}
