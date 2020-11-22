package com.task.kakaopay.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.task.kakaopay.domain.DistributionHistory;
import com.task.kakaopay.vo.GetDsVO;

public interface DistributionHistoryService {

    public void register(DistributionHistory distributionHistory) throws Exception;
    public GetDsVO getOneDsHistory(String token, String x_user_id) throws Exception;
    public void updateisCompleted(int distributionNo, String userIdTaken) throws Exception;
}
