package com.smarttask.taskmanager.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarttask.taskmanager.dto.TaskCompletion;
import com.smarttask.taskmanager.dto.TaskDistribution;
import com.smarttask.taskmanager.service.AdminDashboardService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {
	
	@Autowired
	private AdminDashboardService adminDashboardService;
	
	@GetMapping("/tasks-created-today")
	public ResponseEntity<Long> getTasksCreated() {
		long result = adminDashboardService.getTasksCreatedToday();
		
		return ResponseEntity.ok(result);
	}
	@GetMapping("/total-users")
	public ResponseEntity<Long> getTotalUsers() {
		long result =  adminDashboardService.getTotalRegisteredUsers();
		
		return ResponseEntity.ok(result); 
	}
	
	@GetMapping("/total-completed-task")
	public ResponseEntity<Long> getTotalTasks() {
		long result = adminDashboardService.getTotalCompletedTasks();
		
		return ResponseEntity.ok(result);
	}
	@GetMapping("/task-conversion-rate")
	public ResponseEntity<Map<String, Object>> getTaskConversionRate(){
		
		Map<String, Object> result = adminDashboardService.getCurrentMonthTaskConversionRate();
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/task-completion-rate")
	public ResponseEntity<List<TaskCompletion>> getTaskCompletionRate(){
		
		List<TaskCompletion> result = adminDashboardService.getTaskCompletionOverTime();
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/task-distribution")
	public ResponseEntity<List<TaskDistribution>> getTaskDistributionByRole(){
		
		List<TaskDistribution> result = adminDashboardService.getTaskDistributionByRole();
		
		return ResponseEntity.ok(result);
	}
	
	
}
