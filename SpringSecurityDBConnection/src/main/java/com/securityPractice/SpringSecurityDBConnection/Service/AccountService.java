package com.securityPractice.SpringSecurityDBConnection.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.securityPractice.SpringSecurityDBConnection.DTO.AccountRequestDTO;
import com.securityPractice.SpringSecurityDBConnection.DTO.AccountResponseDTO;
import com.securityPractice.SpringSecurityDBConnection.Entity.Account;
import com.securityPractice.SpringSecurityDBConnection.Repo.AccountJpaRepository;

@Service
public class AccountService {
	
	private AccountJpaRepository jpa;
	
	private PasswordEncoder encode;

	public AccountService(AccountJpaRepository jpa, PasswordEncoder encode) {
		this.jpa = jpa;
		this.encode = encode;
	}
	
	public AccountResponseDTO createAccount(AccountRequestDTO dto) {
		if(jpa.existsByUsername(dto.getUsername())) {  //This is for if the username is already existing
			throw new RuntimeException("Username Exist");
		}
		if(jpa.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email already exist");
		}
		
		Account account = new Account();
		account.setUsername(dto.getUsername());
		account.setCreatedAt(dto.getCreatedAt());
		account.setEmail(dto.getEmail());
		account.setFullname(dto.getFullname());
		account.setPassword(encode.encode(dto.getPassword()));
		account.setPhone(dto.getPhone());
		
		//For role there are only 2 role, admin and user, and Spring security understands the role in this format only (ROLE_USER,ROLE_ADMIN) so we need to convert the data that is sent from frontend to this type and then it needs to be saved in Database.
		//So we cannot pass like this, we have to do this separately
		//account.setRole(dto.getRole());
		account.setRole(normalizeAndValidateRole(dto.getRole()));
		
		//Till here we have created the Account now we want to send the response but not all the details should be send so we will create the request to response
		jpa.save(account);
		return toResponse(account);
		
		
	}
	
	public String normalizeAndValidateRole(String role) {
		if(role==null || role.trim().isEmpty()) {
			return "ROLE_USER";
		}
		
		String r = role.trim().toUpperCase();
		if(!r.startsWith("ROLE_")) {
			r="ROLE_"+r;
		}
		
		if(!(r.equalsIgnoreCase("ROLE_USER") || r.equalsIgnoreCase("ROLE_ADMIN"))) {
			throw new RuntimeException("Invalid Role for Authorization");
		}
		return r;
		
	}
	

	
	public AccountResponseDTO toResponse(Account a) {
		AccountResponseDTO response = new AccountResponseDTO();
		response.setUsername(a.getUsername());
		response.setEmail(a.getEmail());
		response.setPhone(a.getPhone());
		response.setFullname(a.getFullname());
		return response;
	}
	
	
	//This will allow the user to only fetch the details of our own data and not somebody else's
	@PostAuthorize("returnObject.id == principal.id") //But this will give error because there's no Id present in responseDTO so we will make another method getByUsername to execute postAuthorize
	public AccountResponseDTO getById(long id) {	
		Account a = jpa.findById(id).orElseThrow(()->new RuntimeException("Id not found"));
		return toResponse(a);
		
	}
	
	//@PostAuthorize("returnObject.username==authentication.name")  //But here there is one more case, if admin wants to check any details, this won't allow admin also to check any other user so to fix it we will do
	@PostAuthorize("hasRole('ADMIN') or returnObject.username==authentication.name")  //Here if the user is admin then he can check whichever account he wants but if it is user then can check only his account not others.
	public AccountResponseDTO getByUsername(String name) {
		Account a = jpa.findByUsername(name).orElseThrow(()->new RuntimeException("Username not found"));
		return toResponse(a);
	}
	
	
	public List<AccountResponseDTO> getAllAccounts() {

	    List<Account> accounts = jpa.findAll();

	    List<AccountResponseDTO> list = new ArrayList<>();

	    for(Account a : accounts) {
	        AccountResponseDTO dto = new AccountResponseDTO();
	        dto.setPhone(a.getPhone());
	        dto.setFullname(a.getFullname());
	        dto.setEmail(a.getEmail());
	        dto.setUsername(a.getUsername());
	        list.add(dto);
	    }

	    return list;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteById(long id) {
		Account account = jpa.findById(id).orElseThrow(()->new RuntimeException("Id not found"));
		
		jpa.delete(account);
		return "Deleted";
	}
}
