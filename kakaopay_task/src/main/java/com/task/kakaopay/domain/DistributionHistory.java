package com.task.kakaopay.domain;

import java.time.LocalDateTime;

public class DistributionHistory {
	    private String token;
	    private String userIdSplashed;
	    private String userIdTaken;
	    private int allocatedMoney;
	    private boolean completed;
	    private LocalDateTime createdAt;
	    private LocalDateTime modifiedAt;
	    private LocalDateTime endAt;
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
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public LocalDateTime getModifiedAt() {
			return modifiedAt;
		}
		public void setModifiedAt(LocalDateTime modifiedAt) {
			this.modifiedAt = modifiedAt;
		}
		public LocalDateTime getEndAt() {
			return endAt;
		}
		public void setEndAt(LocalDateTime endAt) {
			this.endAt = endAt;
		}

}
