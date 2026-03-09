package com.capgemini.studentManagementSystem.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.studentManagementSystem.entity.Student;
import com.capgemini.studentManagementSystem.repository.StudentRepository;



@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private StudentRepository repo;

    @PostMapping("/uploadImage/{id}")
    public String uploadImage(@PathVariable Long id,
                              @RequestParam MultipartFile file) throws Exception{

        Student s=repo.findById(id).get();
        s.setProfileImage(file.getBytes());

        repo.save(s);

        return "Image Uploaded";
    }

    @PostMapping("/uploadAssignment/{id}")
    public String uploadAssignment(@PathVariable Long id,
                                   @RequestParam MultipartFile file) throws Exception{

        Student s=repo.findById(id).get();
        s.setAssignmentFile(file.getBytes());

        repo.save(s);

        return "Assignment Uploaded";
    }
}