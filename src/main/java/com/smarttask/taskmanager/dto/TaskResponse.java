package com.smarttask.taskmanager.dto;

import java.time.LocalDate;
import com.smarttask.taskmanager.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

	private long id;
	private String title;
	private String description;
	private LocalDate dueDate;
	private Status status;
	private String assignedToName;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public Status getStatus() {
		return status;
	}

	public String getAssignedToName() {
		return assignedToName;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TaskResponse(Long id, String title, String description,
						LocalDate dueDate, Status status, String assignedToName) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
		this.assignedToName = assignedToName;
	}
	

}
