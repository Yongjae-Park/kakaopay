package com.task.kakaopay.domain;

import java.sql.Timestamp;

public class Splash {
    public void setX_user_id(String x_user_id) {
		this.x_user_id = x_user_id;
	}
	public void setX_room_id(String x_room_id) {
		this.x_room_id = x_room_id;
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
	public String getX_user_id() {
		return x_user_id;
	}
	public String getX_room_id() {
		return x_room_id;
	}
	private String token;
    private String x_user_id;
    private String x_room_id;
    private int splashedMoney;
    private int personnel;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private Timestamp endAt;
    
    
}
