package com.task.kakaopay.domain;

import java.sql.Timestamp;

public class Splash {
    public void setXUserId(String xUserId) {
		this.xUserId = xUserId;
	}
	public void setXRoomId(String xRoomId) {
		this.xRoomId = xRoomId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getSplashedMoney() {
		return splashedMoney;
	}
	public void setSplashedMoney(int splashedMoney) {
		this.splashedMoney = splashedMoney;
	}
	public int getPersonnel() {
		return personnel;
	}
	public void setPersonnel(int personnel) {
		this.personnel = personnel;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public Timestamp getEndAt() {
		return endAt;
	}
	public void setEndAt(Timestamp endAt) {
		this.endAt = endAt;
	}
	public String getXUserId() {
		return xUserId;
	}
	public String getXRoomId() {
		return xRoomId;
	}
	private String token;
    private String xUserId;
    private String xRoomId;
    private int splashedMoney;
    private int personnel;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private Timestamp endAt;
    
    
}
