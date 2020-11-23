package com.task.kakaopay.vo;

import java.time.LocalDateTime;

public class GetDsVO {
	private String userIdSplashed;
    private int distributionNo;
    private boolean isCompleted;
    private int allocatedMoney;
    private String userIdTaken;
    private String xRoomId;
    private LocalDateTime expiredAt;
    
	public void setUserIdTaken(String userIdTaken) {
		this.userIdTaken = userIdTaken;
	}
	public void setxRoomId(String xRoomId) {
		this.xRoomId = xRoomId;
	}
	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}
    public LocalDateTime getExpiredAt() {
		return expiredAt;
	}
	public String getxRoomId() {
		return xRoomId;
	}
	public String getUserIdSplashed() {
		return userIdSplashed;
	}
	public void setUserIdSplashed(String userIdSplashed) {
		this.userIdSplashed = userIdSplashed;
	}
	public int getDistributionNo() {
		return distributionNo;
	}
	public void setDistributionNo(int distributionNo) {
		this.distributionNo = distributionNo;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public int getAllocatedMoney() {
		return allocatedMoney;
	}
	public void setAllocatedMoney(int allocatedMoney) {
		this.allocatedMoney = allocatedMoney;
	}
	public String getUserIdTaken() {
		return userIdTaken;
	}
}
