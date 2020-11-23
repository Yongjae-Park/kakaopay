package com.task.kakaopay.domain;

import java.sql.Timestamp;


public class DistributionHistory {
	    private String token;
	    private String userIdSplashed;
	    private String userIdTaken;
	    private int allocatedMoney;
	    private boolean completed;
	    private Timestamp createdAt;
	    private Timestamp modifiedAt;
	    private Timestamp endAt;
	    private String xRoomId;
		public void setXRoomId(String x_room_id) {
			this.xRoomId = x_room_id;
		}
		public String getXRoomId() {
			return xRoomId;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getUserIdSplashed() {
			return userIdSplashed;
		}
		public void setUserIdSplashed(String userIdSplashed) {
			this.userIdSplashed = userIdSplashed;
		}
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
		public boolean isCompleted() {
			return completed;
		}
		public void setCompleted(boolean completed) {
			this.completed = completed;
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

}
