package com.task.kakaopay.service;

import java.util.Map;

import com.task.kakaopay.domain.Splash;
import java.time.LocalDateTime;
import com.task.kakaopay.vo.SelectSplashVO;

public interface SplashService {
	/**
	 * 
	 * @param splash
	 * @return
	 * @throws Exception
	 */
    public Map<String,String> registeSplashAndDistributions(Splash splash) throws Exception;
    /**
     * 
     * @param token
     * @return
     * @throws Exception
     */
    public SelectSplashVO getSplashInfo(String token, String xUserId) throws Exception;
    
    public LocalDateTime selectCreatedAt(String token)throws Exception;
    
}
