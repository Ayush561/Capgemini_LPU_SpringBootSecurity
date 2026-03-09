package com.capgemini.studentManagementSystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.studentManagementSystem.entity.Student;


public interface StudentRepository extends JpaRepository<Student,Long>{

}
