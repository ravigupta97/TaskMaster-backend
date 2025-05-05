package com.smarttask.taskmanager.dto;

import lombok.Data;

@Data
public class DueDateSummary {

	private String dueCategory;
	
	private long count;

	public DueDateSummary(String dueCategory, long count) {
		super();
		this.dueCategory = dueCategory;
		this.count = count;
	}

	public DueDateSummary() {
		super();
	}

	public String getDueCategory() {
		return dueCategory;
	}

	public long getCount() {
		return count;
	}

	public void setDueCategory(String dueCategory) {
		this.dueCategory = dueCategory;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	
	
}
