package com.securityPractice.SpringSecurityDBConnection.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data   //It is in Lombok and will create all the getters setters automatically using this annotation
@NoArgsConstructor //This is in Lombok to create no args constructor
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String role;
	private String email;
	private String phone;
	private String fullname;
	
	private LocalDateTime createdAt = LocalDateTime.now();

}
