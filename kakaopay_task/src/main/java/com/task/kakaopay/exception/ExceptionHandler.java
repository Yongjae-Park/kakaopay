package com.task.kakaopay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ControllerAdvice
public class ExceptionHandler {

	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(CustomRuntimeException.class)
	public ResponseEntity<Error> exception(CustomRuntimeException exception){
		return new ResponseEntity<>(Error.create(exception.getExceptionType()), HttpStatus.OK);
	}
	@NoArgsConstructor
    @AllArgsConstructor
	static class Error{
		private int code;
		private int status;
		private String message;
		public int getCode() {
			return code;
		}
		public int getStatus() {
			return status;
		}
		public String getMessage() {
			return message;
		}
		
		static Error create(BaseExceptionType exceptiontype) {
			return new Error(exceptiontype.getErrorCode(), exceptiontype.getHttpStatus(), exceptiontype.getErrorMessage());
		}
	}
}
