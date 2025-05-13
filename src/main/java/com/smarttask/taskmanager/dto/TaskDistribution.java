package com.smarttask.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDistribution {

	private String role;
	
	private Long taskCount;

	public String getRole() {
		return role;
	}

	public Long getTaskCount() {
		return taskCount;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setTaskCount(Long taskCount) {
		this.taskCount = taskCount;
	}

	public TaskDistribution(String role, Long taskCount) {
		super();
		this.role = role;
		this.taskCount = taskCount;
	}
	
	
	
	
}
