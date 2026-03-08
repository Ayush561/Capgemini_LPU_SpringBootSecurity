package com.capgemini.libraryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capgemini.libraryManagementSystem.entity.BorrowRecord;
import com.capgemini.libraryManagementSystem.service.BorrowService;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public BorrowRecord borrowBook(@RequestParam Long userId,
                                   @RequestParam Long bookId) {

        return borrowService.borrowBook(userId, bookId);
    }
}