package com.smarttask.taskmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarttask.taskmanager.dto.TaskRequest;
import com.smarttask.taskmanager.dto.TaskResponse;
import com.smarttask.taskmanager.service.TaskService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

	@Autowired
	private TaskService taskService;
	
//	@GetMapping("/")
//	public String redirectToIndex() {
//		return "redirect:/index.html";
//	}
	
	
	// Admin or Manager can assign tasks
	@PostMapping(produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> createTask(@RequestBody TaskRequest request) {
		
		TaskResponse response = taskService.createTask(request);

		Map<String, Object> responseBody = new HashMap<>();
		
		responseBody.put("message", "Task Created Successfully!");
		responseBody.put("data", response);
		
		return ResponseEntity.ok(responseBody);
	}
	
	// All authenticated users can get their own tasks
	@GetMapping("/my")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Map<String, Object>> getMyTasks(){
		
		 List<TaskResponse> response = taskService.getMyTasks();
		 
		 Map<String, Object> responseBody = new HashMap<>();
			
		responseBody.put("message", "Your Task retrieved");
		responseBody.put("data", response);
			
		return ResponseEntity.ok(responseBody);
		 
	}
	
	// Admin or Manager can view all tasks
	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> getAllTasks(){
		
		 List<TaskResponse> response = taskService.getAllTasks();
		 
		 Map<String, Object> responseBody = new HashMap<>();
			
		responseBody.put("message", "All Tasks retrieved");
		responseBody.put("data", response);
			
		return ResponseEntity.ok(responseBody);
		 
	}
	
	
	 // Employee can update only their own task status
	@PutMapping("/{taskId}/status")
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> updateStatus(@PathVariable Long taskId, @RequestBody TaskRequest request) {
		
		TaskResponse response =  taskService.updateTaskStatus(taskId, request);
		
		Map<String, Object> responseBody = new HashMap<>();
		
		responseBody.put("message", "Task Status Updated Successfully!");
		responseBody.put("data", response);
		
		return ResponseEntity.ok(responseBody);
		
	}
	
	// Admin or Manager can update task details
	@PutMapping("/{taskId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> updateTaskDetails(@PathVariable Long taskId,
	                                      @RequestBody TaskRequest request) {
		
		TaskResponse response = taskService.updateTaskDetails(taskId, request);
		
		Map<String, Object> responseBody = new HashMap<>();
		
		responseBody.put("message", "Task Updated Successfully! ");
		responseBody.put("data", response);
		
		return ResponseEntity.ok(responseBody);
	    
	}
	
	// Admin or Manager can delete a task
	@DeleteMapping("/{taskId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable Long taskId) {
		 
		taskService.deleteTask(taskId);
		
		Map<String, Object> responseBody = new HashMap<>();
		
		responseBody.put("message", "Task Deleted Successfully! ");
		responseBody.put("data", null);
		
		return ResponseEntity.ok(responseBody);
	    
	}
	
}
