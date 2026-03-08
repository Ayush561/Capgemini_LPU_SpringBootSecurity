package com.securityPractice.SpringSecurityDBConnection.Controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.securityPractice.SpringSecurityDBConnection.DTO.AccountRequestDTO;
import com.securityPractice.SpringSecurityDBConnection.DTO.AccountResponseDTO;
import com.securityPractice.SpringSecurityDBConnection.Entity.Account;
import com.securityPractice.SpringSecurityDBConnection.Service.AccountService;

@RestController
public class AccountController {
	private AccountService service;

	public AccountController(AccountService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public AccountResponseDTO create(@RequestBody AccountRequestDTO dto) {
		System.out.println("Account created");
		return service.createAccount(dto);
	}
	
	
	//Here if we are a user-1 and we want to find-id let's say 2 or 3 we can easily find it, but this not a good practice, as a user you must only find your account and not others
	//then we use PostAuthorize() annotation in the service layer.
	@GetMapping("/find-id/{id}")
	public AccountResponseDTO getById(@PathVariable Long id) {
		AccountResponseDTO a=service.getById(id);
		return a;
	}
	
	@GetMapping("/find-name/{name}")
	public AccountResponseDTO getByUsername(@PathVariable String name) {
		AccountResponseDTO a = service.getByUsername(name);
		return a;
	}
	
	//@GetMapping("/find-all")
	@GetMapping("/admin/find-all")  //We can do @GetMapping("/find-all") also but we need to add another requestMatcher in the securityconfig to avoid that we are doing this
	public List<AccountResponseDTO> findAll() {
	    return service.getAllAccounts();
	}
	
	
	//This method is used to check whether the logged_in account is user or admin
	@GetMapping("/public/profile")
	public String profile(Authentication authentication) {
		return authentication.getName() +" "+authentication.getAuthorities();  //Get name is for the username and getAuthorities is for the role the user is having.
	}
	
	//@DeleteMapping("/admin/delete-id/{id}") //Either we can do like this or we can use @PreAuthorize in the service class in delete method
	@DeleteMapping("/delete-id/{id}")
	public String deleteById(@PathVariable long id) {
		return service.deleteById(id);
	}
	
	

}
