package com.smarttask.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

	private String message;
	
	private String token;

	public String getToken() {
		return token;
	}

	public String getMessage() {
		return message;
	}

	public AuthResponse(String message, String token) {
		super();
		this.message = message;
		this.token = token;
	}

	

	

	
}
