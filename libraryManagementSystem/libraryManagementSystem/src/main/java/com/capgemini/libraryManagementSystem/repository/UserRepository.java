package com.capgemini.libraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capgemini.libraryManagementSystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}