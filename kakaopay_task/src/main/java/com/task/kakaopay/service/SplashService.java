package com.task.kakaopay.service;

import java.util.List;
import java.util.Map;

import com.task.kakaopay.domain.Splash;
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
    public SelectSplashVO getSplashInfo(String token) throws Exception;
    
    
    
}
