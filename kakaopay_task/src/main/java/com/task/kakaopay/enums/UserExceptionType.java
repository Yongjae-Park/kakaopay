package com.task.kakaopay.enums;

import com.task.kakaopay.exception.BaseExceptionType;

import lombok.Getter;

public enum UserExceptionType implements BaseExceptionType{
	
	ANYONE_BUT_NOT_YOURSELF(1001, 200, "뿌리기한 본인은 받을 수 없습니다."),
    DUPLICATED_USER(1002, 200, "이미 뿌리기를 받아간 유저입니다."),
    WRONG_ROOM_ACCESS(1003, 200, "잘못된 대화방입니다."),
    HAS_EXPIRED_SPLASH(1004, 200, "만료된 뿌리기건 입니다."),
	HAS_EXPIRED_FOR_LOOK_UP(1005, 200, "받을 수 있는 유효시간이 초과하였습니다."),
	ONLY_SPLASHED_USER(1006, 200, "뿌리기한 유저만 조회할 수 있습니다.");
	
	private int errorCode;
	private int httpStatus;
	private String errorMessage;
	
	public int getErrorCode() {
		return errorCode;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	UserExceptionType(int errorCode, int httpStatus, String errorMessage) {
	        this.errorCode = errorCode;
	        this.httpStatus = httpStatus;
	        this.errorMessage = errorMessage;
	    }
}
