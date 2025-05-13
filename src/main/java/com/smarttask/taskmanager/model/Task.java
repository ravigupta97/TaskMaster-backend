package com.smarttask.taskmanager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private LocalDate dueDate;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(nullable = false, updatable = false)
	private LocalDate createdDate;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	@PreUpdate
	public void updateTimestamp() {
		this.updatedAt = LocalDateTime.now(); 
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdDate = LocalDate.now();
		this.updatedAt = LocalDateTime.now();
	}

		
}
