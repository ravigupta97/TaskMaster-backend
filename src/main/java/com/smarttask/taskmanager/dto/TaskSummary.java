package com.smarttask.taskmanager.dto;

import lombok.Data;

@Data
public class TaskSummary {

	private long totalTasks;
	
	private long pending;
	
	private long inProgress;
	
	private long toDo;
	
	private long completed;

	public TaskSummary(long totalTasks, long pending, long inProgress, long toDo, long completed) {
		super();
		this.totalTasks = totalTasks;
		this.pending = pending;
		this.inProgress = inProgress;
		this.toDo = toDo;
		this.completed = completed;
	}

	public TaskSummary() {
		super();
	}

	public long getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(long totalTasks) {
		this.totalTasks = totalTasks;
	}

	public long getPending() {
		return pending;
	}

	public void setPending(long pending) {
		this.pending = pending;
	}

	public long getInProgress() {
		return inProgress;
	}

	public void setInProgress(long inProgress) {
		this.inProgress = inProgress;
	}

	public long getToDo() {
		return toDo;
	}

	public void setToDo(long toDo) {
		this.toDo = toDo;
	}

	public long getCompleted() {
		return completed;
	}

	public void setCompleted(long completed) {
		this.completed = completed;
	}
	
	
	
	
	
}
