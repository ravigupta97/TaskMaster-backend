package com.smarttask.taskmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarttask.taskmanager.dto.ApiResponse;
import com.smarttask.taskmanager.dto.DueDateSummary;
import com.smarttask.taskmanager.dto.TaskStatusCount;
import com.smarttask.taskmanager.dto.TaskSummary;
import com.smarttask.taskmanager.dto.UserPerformance;
import com.smarttask.taskmanager.service.AnalyticsService;



@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

	@Autowired
	private AnalyticsService analyticsService;
	
	@GetMapping("/task-summary")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getSummaryAnalytics(){
		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	    System.out.println("User accessing /task-summary: " + auth.getName());
//	    System.out.println("Authorities: " + auth.getAuthorities());
	    
		TaskSummary response = analyticsService.getTaskSummary();
		
		Map<String, Object> responseBody = new HashMap<>();
		
		responseBody.put("message", "Analytics Summary");
		responseBody.put("data", response);
	
		return ResponseEntity.ok(responseBody);
	}
	
	@GetMapping("/user-performance-summary")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getPerformanceAnalytics() {
	    
		List<UserPerformance> data = analyticsService.getUserPerformance();
	    
		Map<String, Object> responseBody = new HashMap<>();
		
		responseBody.put("message", "User productivity");
		responseBody.put("data", data);
	
		return ResponseEntity.ok(responseBody);

	}
	
	@GetMapping("/due-date-summary")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getDueDateAnalytics() {
	    
		List<DueDateSummary> data = analyticsService.getDueDateSummary();
		
		Map<String, Object> responseBody = new HashMap<>();
		
		responseBody.put("message", "Task due date summary");
		responseBody.put("data", data);
	
		return ResponseEntity.ok(responseBody);
	    
	}
	
	@GetMapping("/status-summary")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getStatusAnalytics() {
	    
		List<TaskStatusCount> data = analyticsService.getStatusSummary();
		
		Map<String, Object> responseBody = new HashMap<>();
		
		responseBody.put("message", "Task status distribution");
		responseBody.put("data", data);
	
		return ResponseEntity.ok(responseBody);
	    
	}
	

}
