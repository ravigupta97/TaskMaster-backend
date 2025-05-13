package com.smarttask.taskmanager.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smarttask.taskmanager.model.Status;
import com.smarttask.taskmanager.model.Task;
import com.smarttask.taskmanager.model.User;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUser(User user);
	
	List<Task> findByStatus(Status status);
	
	List<Task> findByDueDateBefore(LocalDate date);
	
	long countByStatus(Status status);
	
	long countByUser(User user);
	
	long countByCreatedDate(LocalDate startDate);
	
	long countByUserAndStatus(User user, Status status);
	
	long countByDueDateBeforeAndStatusNot(LocalDate date, Status status);
	
	long countByDueDateEqualsAndStatusNot(LocalDate date, Status status);
	
	long countByDueDateBetweenAndStatusNot(LocalDate startDate, LocalDate endDate, Status status);

	//long countByStatusAndCreatedAtBetween(Status status, LocalDateTime startDate, LocalDateTime endDate);

	//long countByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
	
	long countByStatusAndUpdatedAtBetween(Status status, LocalDateTime startDate, LocalDateTime endDate);

	long countByUpdatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

	
//	@Query("SELECT FUNCTION('DATE', t.updatedAt), COUNT(t) " +
//	           "FROM Task t " +
//	           "WHERE t.status = 'DONE' " +
//	           "GROUP BY FUNCTION('DATE', t.updatedAt) " +
//	           "ORDER BY FUNCTION('DATE', t.updatedAt)")
//	    List<Object[]> countTasksCompletedByDate();
	    
	    
	@Query("SELECT r.name, COUNT(t) " +
		       "FROM Task t " +
		       "JOIN t.user u " +
		       "JOIN u.roles r " +
		       "GROUP BY r.name")
		List<Object[]> countTasksByUserRole();
     	
     	
// 	@Query("SELECT COUNT(t) FROM Task t " +
//            "WHERE t.status = 'DONE' " +
//            "AND FUNCTION('MONTH', t.updatedAt) = FUNCTION('MONTH', CURRENT_DATE) " +
//            "AND FUNCTION('YEAR', t.updatedAt) = FUNCTION('YEAR', CURRENT_DATE)")
//     	Long countTasksCompletedThisMonth();
	
 	
 	@Query(value = """
 		    SELECT TO_CHAR(t.updated_at, 'Mon YYYY') AS month,
 		           ROUND(
 		               SUM(CASE WHEN t.status = 'DONE' THEN 1 ELSE 0 END)::decimal * 100 / COUNT(*),
 		               2
 		           ) AS completionRate
 		    FROM task t
 		    WHERE t.updated_at IS NOT NULL
 		    GROUP BY TO_CHAR(t.updated_at, 'Mon YYYY'), DATE_TRUNC('month', t.updated_at)
 		    ORDER BY DATE_TRUNC('month', t.updated_at)
 		    """, nativeQuery = true)
 		List<Object[]> getMonthlyTaskCompletionRates();

	


//	@Query(value = "SELECT t.assigned_to_email, COUNT(*),"+ 
//				" AVG(TIMESTAMPDIFF(SECOND, t.created_at, t.completed_at)) " +
//				"FROM tasks t WHERE t.status = 'DONE' GROUP BY t.assigned_to_email",
//				nativeQuery = true)
//	List<Object[]> getUserPerformanceStats();
//	
//		
//	
//		@Query(value = "SELECT " +
//		        "CASE " +
//		        "WHEN t.due_date = CURRENT_DATE THEN 'Due Today' " +
//		        "WHEN t.due_date < CURRENT_DATE THEN 'Overdue' " +
//		        "WHEN t.due_date BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL 7 DAY) THEN 'Due This Week' " +
//		        "ELSE 'Future' END AS category, " +
//		        "COUNT(*) " +
//		        "FROM tasks t " +
//		        "WHERE t.status != 'DONE' " +
//		        "GROUP BY category", 
//		        nativeQuery = true)
//		List<Object[]> getDueDateAnalytics();
//
//	@Query("SELECT new com.smarttask.taskmanager.dto.TaskStatusCount(t.status, COUNT(t)) FROM Task t GROUP BY t.status")
//	List<TaskStatusCount> countTasksByStatus();
//	
//	@Query("SELECT t FROM Task t WHERE t.dueDate < CURRENT_DATE")
//	List<Task> findOverdueTasks();
//	
//	@Query("SELECT t FROM Task t WHERE t.dueDate = CURRENT_DATE")
//	List<Task> findTodayDueTasks();
//	
//	@Query(value = "SELECT * FROM task t WHERE t.due_date BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL 7 DAY)", nativeQuery = true)
//	List<Task> findThisWeekDueTasks();

}
