package com.capgemini.studentManagementSystem.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import com.capgemini.studentManagementSystem.entity.Student;
import com.capgemini.studentManagementSystem.repository.StudentRepository;



@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Student createStudent(Student s){
        return repo.save(s);
    }

    @Cacheable("students")
    @PostAuthorize("returnObject.email == authentication.name or hasRole('ADMIN')")
    public Optional<Student> getStudentById(Long id){
        return repo.findById(id);
    }

    public Page<Student> getAllStudents(Pageable pageable){
        return repo.findAll(pageable);
    }

    public Student updateStudent(Long id,Student s){
        Student old=repo.findById(id).get();

        old.setName(s.getName());
        old.setCourse(s.getCourse());
        old.setEmail(s.getEmail());
        old.setMarks(s.getMarks());

        return repo.save(old);
    }

    public void deleteStudent(Long id){
        repo.deleteById(id);
    }
}
