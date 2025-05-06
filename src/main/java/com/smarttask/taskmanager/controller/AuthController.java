package com.smarttask.taskmanager.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarttask.taskmanager.dto.AuthResponse;
import com.smarttask.taskmanager.dto.LoginRequest;
import com.smarttask.taskmanager.dto.RegisterRequest;
import com.smarttask.taskmanager.model.*;
import com.smarttask.taskmanager.repository.*;
import com.smarttask.taskmanager.repository.UserRepository;
import com.smarttask.taskmanager.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/debug")
	public ResponseEntity<String> debug(){
		return ResponseEntity.ok("Backend reached");
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request){
		
		if(userRepo.findByEmail(request.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("Email is already in use.");
		}
		
		Set<Role> userRoles = request.getRoles().stream()
	            .map(roleName -> roleRepo.findByName(roleName)
	                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
	            .collect(Collectors.toSet());
		
		User newUser = new User();
		newUser.setName(request.getName());
		newUser.setEmail(request.getEmail());
		newUser.setPassword(passwordEncoder.encode(request.getPassword()));
		newUser.setRoles(userRoles);
		
		userRepo.save(newUser);
		return ResponseEntity.ok("User Registered Successfully");
	}
	
	@PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
            request.getEmail(), request.getPassword()));

        UserDetails userDetails = userRepo.findByEmail(request.getEmail())
            .map(user -> new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                    .collect(Collectors.toList())
            )).orElseThrow();

        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse("User Logged in Successfully! ",jwt));
        
        
	}
}
