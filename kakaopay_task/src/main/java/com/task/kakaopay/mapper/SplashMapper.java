package com.task.kakaopay.mapper;

import java.time.LocalDateTime;
import java.util.List;
import com.task.kakaopay.vo.LookUpInfoVO;

import com.task.kakaopay.domain.Splash;

public interface SplashMapper {
    public void createSplash(Splash splash) throws Exception;
    public List<LookUpInfoVO> selectAllLookUpInfo(String token) throws Exception;
    public LocalDateTime selectCreatedAt(String token)throws Exception;
}
