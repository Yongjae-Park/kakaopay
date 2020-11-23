package com.task.kakaopay.exception;

public class CustomRuntimeException extends RuntimeException{
	//이미 받아간 유저 받기 요청시 사용자 예외
//	public DuplicatedUserIdException(String msg) {
//		super(msg);
//	}
	private BaseExceptionType exceptionType;
	
	public BaseExceptionType getExceptionType() {
		return exceptionType;
	}

	public CustomRuntimeException(BaseExceptionType exceptionType) {
		super(exceptionType.getErrorMessage());
		this.exceptionType = exceptionType;
	}
}

