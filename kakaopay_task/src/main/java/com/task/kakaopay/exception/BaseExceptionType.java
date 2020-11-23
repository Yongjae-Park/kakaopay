package com.task.kakaopay.exception;

public interface BaseExceptionType {

	int getErrorCode();
	int getHttpStatus();
	String getErrorMessage();
}
