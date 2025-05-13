package com.smarttask.taskmanager.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarttask.taskmanager.dto.TaskCompletion;
import com.smarttask.taskmanager.dto.TaskDistribution;
import com.smarttask.taskmanager.model.Status;
import com.smarttask.taskmanager.repository.TaskRepository;
import com.smarttask.taskmanager.repository.UserRepository;

@Service
public class AdminDashboardService {

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Long getTasksCreatedToday() {
		LocalDate today = LocalDate.now();
		long response = taskRepo.countByCreatedDate(today);
		
		return response;
	}
	
	public Long getTotalRegisteredUsers() {
		long response =  userRepo.count();
		
		return response;
	}
	
	public Long getTotalCompletedTasks() {
		long response = taskRepo.countByStatus(Status.DONE);
		
		return response;
	}
	
	public Map<String, Object> getCurrentMonthTaskConversionRate(){
		
		Map<String, Object> response = new HashMap<>();
		
		LocalDateTime start = LocalDate.now().withDayOfMonth(1).atStartOfDay();
		LocalDateTime end = LocalDateTime.now();
		
		long done = taskRepo.countByStatusAndUpdatedAtBetween(Status.DONE, start, end);
		long total = taskRepo.countByUpdatedAtBetween(start, end);
		
		double rate = (total == 0) ? 0 :((double) done / total)*100;
		
		response.put("ConversionRate", Math.round(rate * 100.0) / 100.0);
		
		return response;
	
	}
	
	public List<TaskCompletion> getTaskCompletionOverTime(){
		
		List<Object[]> response = taskRepo.getMonthlyTaskCompletionRates();
		
		List<TaskCompletion> data = new ArrayList<>();
		
		for(Object[] row : response) {
			String month = (String) row[0];
			Double completionRate = ((Number) row[1]).doubleValue();
			
			data.add(new TaskCompletion(month, completionRate));
		}
		
		return data;
	}
	
	public List<TaskDistribution> getTaskDistributionByRole(){
		
		List<Object[]> response = taskRepo.countTasksByUserRole();
		
		return response.stream()
				.map(obj -> new TaskDistribution(obj[0].toString(), (Long)obj[1]))
				.collect(Collectors.toList());
	}
}
