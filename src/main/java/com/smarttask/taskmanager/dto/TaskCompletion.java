package com.smarttask.taskmanager.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskCompletion {

	private String month;
	
	private Double completionRate;

	

	public String getMonth() {
		return month;
	}

	public Double getCompletedTasks() {
		return completionRate;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setCompletedTasks(Double completionRate) {
		this.completionRate = completionRate;
	}

	public TaskCompletion(String month, Double completionRate) {
		super();
		this.month = month;
		this.completionRate = completionRate;
	}
	
	
	
}
