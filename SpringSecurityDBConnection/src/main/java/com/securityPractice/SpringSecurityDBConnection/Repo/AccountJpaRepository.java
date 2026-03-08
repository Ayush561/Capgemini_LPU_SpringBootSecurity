package com.securityPractice.SpringSecurityDBConnection.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securityPractice.SpringSecurityDBConnection.Entity.Account;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
	
	//findBy,readBy,getBy and existsBy are already present in the repository
	public Optional<Account> findByUsername(String name);
	
	public boolean existsByUsername(String name);
	
	public boolean existsByEmail(String email);
	

}
