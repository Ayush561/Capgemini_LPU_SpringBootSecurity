package com.capgemini.studentManagementSystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.studentManagementSystem.entity.User;


public interface UserRepository extends JpaRepository<User,Long>{

    User findByUsername(String username);

}
