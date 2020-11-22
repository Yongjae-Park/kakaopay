package com.task.kakaopay.domain;

public class SplashRequestDto {
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
	private int splashedMoney;
	private int personnel;
}
