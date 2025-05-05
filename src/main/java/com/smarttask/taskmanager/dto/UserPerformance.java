package com.smarttask.taskmanager.dto;

import lombok.Data;

@Data
public class UserPerformance {

	private String username;
	
	private long totalTasks;
	
	private long completedTasks;

	public UserPerformance(String username, long totalTasks, long completedTasks) {
		super();
		this.username = username;
		this.totalTasks = totalTasks;
		this.completedTasks = completedTasks;
	}

	public UserPerformance() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public long getTotalTasks() {
		return totalTasks;
	}

	public long getCompletedTasks() {
		return completedTasks;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setTotalTasks(long totalTasks) {
		this.totalTasks = totalTasks;
	}

	public void setCompletedTasks(long completedTasks) {
		this.completedTasks = completedTasks;
	}
	
	
	
	
	
	
	
	
	
}
