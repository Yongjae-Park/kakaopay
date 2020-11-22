package com.task.kakaopay.vo;

import java.sql.Timestamp;


public class LookUpInfoVO {
	private String token;
	private Timestamp createdAt;
	private Timestamp expiredAt;
	private int splashedMoney;
	private int allocatedMoney;
	private String userIdTaken;
	private boolean isCompleted;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getExpiredAt() {
		return expiredAt;
	}
	public void setExpiredAt(Timestamp expiredAt) {
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
