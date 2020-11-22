package com.task.kakaopay.vo;

public class GetDsVO {
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
	private String userIdSplashed;
    private int distributionNo;
    private boolean isCompleted;
    private int allocatedMoney;
}
