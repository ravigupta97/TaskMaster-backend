package com.smarttask.taskmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smarttask.taskmanager.dto.TaskRequest;
import com.smarttask.taskmanager.dto.TaskResponse;
import com.smarttask.taskmanager.model.Task;
import com.smarttask.taskmanager.model.User;
import com.smarttask.taskmanager.repository.TaskRepository;
import com.smarttask.taskmanager.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private User getCurrentUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return userRepo.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException("User Not Found"));
	}
	
	/**
	 * @param request
	 * @return
	 */
	public TaskResponse createTask(TaskRequest request) {
		
		User assignee = userRepo.findById(request.getAssignedToUserId())
				.orElseThrow(() -> new EntityNotFoundException("Assigned user not found"));
		
		Task task = new Task();
		task.setTitle(request.getTitle());
		task.setDescription(request.getDescription());
		task.setDueDate(request.getDueDate());
		task.setStatus(request.getStatus());
		task.setUser(assignee);
		
		Task saved = taskRepo.save(task);
		
		return new TaskResponse(saved.getId(), saved.getTitle(), 
				saved.getDescription(), saved.getDueDate(), 
				saved.getStatus(), assignee.getName());
		
		
	}
	
	public List<TaskResponse> getMyTasks(){
		
		User user = getCurrentUser();
		
		List<Task> tasks = taskRepo.findByUser(user);
		
		return tasks.stream().map(task -> new TaskResponse(task.getId(), task.getTitle(),
				task.getDescription(),task.getDueDate(), task.getStatus(), task.getUser()
				.getName())).collect(Collectors.toList());
	}
	
	public List<TaskResponse> getAllTasks(){
		
		return taskRepo.findAll().stream().map(task -> new TaskResponse(task.getId(), task.getTitle(),
				task.getDescription(),task.getDueDate(), task.getStatus(), task.getUser()
				.getName())).collect(Collectors.toList());
		
	}
	
	
	public TaskResponse updateTaskStatus(Long taskId, TaskRequest request) {
		
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new EntityNotFoundException("Task not found"));
		
		User currentUser = getCurrentUser();
		
		if(!task.getUser().getId().equals(currentUser.getId())){
			throw new SecurityException("You can only update your own tasks.");
		}
		
		task.setStatus(request.getStatus());
		
		Task updated = taskRepo.save(task);
		
		return new TaskResponse(updated.getId(), updated.getTitle(), 
				updated.getDescription(), updated.getDueDate(), updated.getStatus(), 
				updated.getUser().getName());
	}
	
	public TaskResponse updateTaskDetails(Long taskId, TaskRequest request) {
	    
		Task task = taskRepo.findById(taskId)
	            .orElseThrow(() -> new EntityNotFoundException("Task not found"));

	    task.setTitle(request.getTitle());
	    task.setDescription(request.getDescription());
	    task.setDueDate(request.getDueDate());
	    Task updated = taskRepo.save(task);

	    return new TaskResponse(
	            updated.getId(), updated.getTitle(), updated.getDescription(),
	            updated.getDueDate(), updated.getStatus(), updated.getUser().getName()
	    );
	}
	
	public void deleteTask(Long taskId) {
	    
		Task task = taskRepo.findById(taskId)
	            .orElseThrow(() -> new EntityNotFoundException("Task not found"));
	    
	    taskRepo.delete(task);
	}
	
	public Task updateTask(Long taskId, TaskRequest request) {
		
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new EntityNotFoundException("Task not found"));
		
		task.setTitle(request.getTitle());
		task.setDescription(request.getDescription());
		task.setDueDate(request.getDueDate());
		
		 // If status is being updated
//	    if (request.getStatus() != null && request.getStatus() != task.getStatus()) {
//	        task.setStatus(request.getStatus());
//
//	        // Only set completedAt if marking COMPLETE for the first time
//	        if (request.getStatus() == Status.DONE && task.getCompletedAt() == null) {
//	            task.setCompletedAt(LocalDateTime.now());
//	        }
//
//	        // Optional: If status changes from COMPLETED to other (reset completedAt)
//	        else if (request.getStatus() != Status.DONE && task.getCompletedAt() != null) {
//	            task.setCompletedAt(null); // Only if you want to reset
//	        }
//	    }

	    return taskRepo.save(task);
	}
}
