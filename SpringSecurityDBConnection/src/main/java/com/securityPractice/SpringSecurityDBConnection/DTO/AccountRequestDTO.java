package com.securityPractice.SpringSecurityDBConnection.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AccountRequestDTO {
	private String username;
	private String password;
	private String role;
	private String email;
	private String phone;
	private String fullname;
	
	private LocalDateTime createdAt = LocalDateTime.now();

}
