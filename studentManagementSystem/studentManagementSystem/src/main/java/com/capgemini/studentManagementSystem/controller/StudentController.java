package com.capgemini.studentManagementSystem.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.studentManagementSystem.entity.Student;
import com.capgemini.studentManagementSystem.service.StudentService;



@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Student createStudent(@RequestBody Student s){
        return service.createStudent(s);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id){
        return service.getStudentById(id).get();
    }

    @GetMapping
    public Page<Student> getAllStudents(
            @RequestParam int page,
            @RequestParam int size){

        return service.getAllStudents(PageRequest.of(page,size));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,@RequestBody Student s){
        return service.updateStudent(id,s);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        service.deleteStudent(id);
        return "Deleted";
    }
}