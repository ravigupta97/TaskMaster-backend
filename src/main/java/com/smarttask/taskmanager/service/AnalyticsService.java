package com.smarttask.taskmanager.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarttask.taskmanager.dto.DueDateSummary;
import com.smarttask.taskmanager.dto.TaskStatusCount;
import com.smarttask.taskmanager.dto.TaskSummary;
import com.smarttask.taskmanager.dto.UserPerformance;
import com.smarttask.taskmanager.model.Status;
import com.smarttask.taskmanager.repository.TaskRepository;
import com.smarttask.taskmanager.repository.UserRepository;



@Service
public class AnalyticsService {

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public TaskSummary getTaskSummary() {
		
		long totalTasks = taskRepo.count();
		long pending = taskRepo.countByStatus(Status.PENDING);
		long inProgress = taskRepo.countByStatus(Status.IN_PROGRESS);
		long toDo = taskRepo.countByStatus(Status.TODO);
		long completed = taskRepo.countByStatus(Status.DONE);
		
		return new TaskSummary(totalTasks, pending, inProgress, toDo, completed);
	}
	
	
	public List<UserPerformance> getUserPerformance() {
	    
		return userRepo.findAll().stream().map(user -> {
			long total = taskRepo.countByUser(user);
			long completed = taskRepo.countByUserAndStatus(user, Status.DONE);
			return new UserPerformance(user.getName(), total, completed);
		}).collect(Collectors.toList());
		
	}
	
	
	public List<TaskStatusCount> getStatusSummary() {
	    
		return Arrays.stream(Status.values())
				.map(status -> new TaskStatusCount(status, taskRepo.countByStatus(status)))
				.collect(Collectors.toList());
		
	}
	
	public List<DueDateSummary> getDueDateSummary(){
		
		LocalDate today = LocalDate.now();
		LocalDate weekEnd = today.plusDays(7);
		
		long overDue = taskRepo.countByDueDateBeforeAndStatusNot(today, Status.DONE);
		long dueToday = taskRepo.countByDueDateEqualsAndStatusNot(today, Status.DONE);
		long dueThisWeek = taskRepo.countByDueDateBetweenAndStatusNot(today.plusDays(1), weekEnd, Status.DONE);
		
		return List.of(
			new DueDateSummary("OverDue", overDue),
			new DueDateSummary("Due Today", dueToday),
			new DueDateSummary("Due This Week", dueThisWeek)
		);
		
	}
	

}
