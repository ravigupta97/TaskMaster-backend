package com.smarttask.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
	
	private String message;
	
	private T data;

	public ApiResponse(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}
	
	
}
