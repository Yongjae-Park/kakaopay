package com.task.kakaopay.vo;

public class UserVO {
    public String getUserIdTaken() {
		return userIdTaken;
	}
	public void setUserIdTaken(String userIdTaken) {
		this.userIdTaken = userIdTaken;
	}
	public int getAllocatedMoney() {
		return allocatedMoney;
	}
	public void setAllocatedMoney(int allocatedMoney) {
		this.allocatedMoney = allocatedMoney;
	}
	private String userIdTaken;
    private int allocatedMoney;
}
