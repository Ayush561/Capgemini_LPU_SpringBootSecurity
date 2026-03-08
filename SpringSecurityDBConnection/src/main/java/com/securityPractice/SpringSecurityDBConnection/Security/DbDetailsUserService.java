package com.securityPractice.SpringSecurityDBConnection.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.securityPractice.SpringSecurityDBConnection.Entity.Account;
import com.securityPractice.SpringSecurityDBConnection.Repo.AccountJpaRepository;

@Service
public class DbDetailsUserService implements UserDetailsService {
	
	
	private AccountJpaRepository repository;
	
	public DbDetailsUserService(AccountJpaRepository repository) {
		this.repository = repository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //Security context won't directly connect with database to find the User, it will interact with UserDetails class to find the user if present
		
		Account account=repository.findByUsername(username)
							   .orElseThrow(()-> new RuntimeException("Username not found"));
		List<SimpleGrantedAuthority> authroties = new ArrayList<>();
		authroties.add(new SimpleGrantedAuthority(account.getRole()));
		
		return new User(account.getUsername(),account.getPassword(), authroties);  //This will accept username and password in the form of String but not the role, It accepts the role in the form of Collection which is SimpleGrantedAuthority type.
	}

}
