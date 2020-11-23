package com.task.kakaopay.vo;

import java.time.LocalDateTime;

public class LookUpInfoVO {
	private String token;
	private LocalDateTime createdAt;
	private LocalDateTime expiredAt;
	private int splashedMoney;
	private int allocatedMoney;
	private String userIdTaken;
	private boolean isCompleted;
	private String userIdSplashed;
	public String getUserIdSplashed() {
		return userIdSplashed;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}
	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}
	public int getSplashedMoney() {
		return splashedMoney;
	}
	public void setSplashedMoney(int splashedMoney) {
		this.splashedMoney = splashedMoney;
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
	public void setUserIdTaken(String userIdTaken) {
		this.userIdTaken = userIdTaken;
	}
	public boolean getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
}
