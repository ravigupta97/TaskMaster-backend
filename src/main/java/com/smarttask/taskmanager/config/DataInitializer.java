package com.smarttask.taskmanager.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarttask.taskmanager.model.Role;
import com.smarttask.taskmanager.repository.RoleRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	public void initRoles() {
		List<String> roleNames = List.of("ADMIN", "EMPLOYEE", "MANAGER");
		
		for(String roleName : roleNames) {
			if(roleRepository.findByName(roleName).isEmpty()) {
				Role role = new Role();
				role.setName(roleName);
				roleRepository.save(role);
			}
		}
	}
}
