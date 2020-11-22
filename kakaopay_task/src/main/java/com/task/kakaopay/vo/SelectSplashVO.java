package com.task.kakaopay.vo;

import java.sql.Timestamp;
import java.util.List;

public class SelectSplashVO {
	private Timestamp createdAt;
	private int splashedMoney;
	private int completedMoney;
	private List<UserVO> userList;
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public int getSplashedMoney() {
		return splashedMoney;
	}
	public void setSplashedMoney(int splashedMoney) {
		this.splashedMoney = splashedMoney;
	}
	public int getCompletedMoney() {
		return completedMoney;
	}
	public void setCompletedMoney(int completedMoney) {
		this.completedMoney = completedMoney;
	}
	public List<UserVO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}

}
