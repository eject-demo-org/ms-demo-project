package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NO_CONTENT)
public class DataNotFound extends RuntimeException {
	public DataNotFound(String message) {
		super(message);
	}
}
