package com.task.kakaopay.service;

import java.util.List;

import com.task.kakaopay.domain.Splash;
import com.task.kakaopay.vo.SelectSplashVO;

public interface SplashService {
    public void registeSplash(Splash splash) throws Exception;
    
    public SelectSplashVO getSplashInfo(String token) throws Exception;
    
    
    
}
