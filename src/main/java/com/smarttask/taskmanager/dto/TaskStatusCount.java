package com.smarttask.taskmanager.dto;

import com.smarttask.taskmanager.model.Status;

import lombok.Data;

@Data
public class TaskStatusCount {

	private Status status;
	
	private long count;

	public TaskStatusCount(Status status, long count) {
		super();
		this.status = status;
		this.count = count;
	}

	public TaskStatusCount() {
		super();
	}

	public Status getStatus() {
		return status;
	}

	public long getCount() {
		return count;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	
	
}
