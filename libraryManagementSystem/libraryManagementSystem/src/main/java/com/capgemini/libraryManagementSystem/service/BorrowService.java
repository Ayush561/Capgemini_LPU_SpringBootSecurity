package com.capgemini.libraryManagementSystem.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.libraryManagementSystem.entity.Book;
import com.capgemini.libraryManagementSystem.entity.BorrowRecord;
import com.capgemini.libraryManagementSystem.entity.User;
import com.capgemini.libraryManagementSystem.repository.BookRepository;
import com.capgemini.libraryManagementSystem.repository.BorrowRecordRepository;
import com.capgemini.libraryManagementSystem.repository.UserRepository;

@Service
public class BorrowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRecordRepository borrowRepository;

    public BorrowRecord borrowBook(Long userId, Long bookId) {

        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        if (!book.isAvailable()) {
            throw new RuntimeException("Book not available");
        }

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);   // ✅ Correct entity User
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setReturned(false);

        book.setAvailable(false);
        bookRepository.save(book);

        return borrowRepository.save(record);
    }
}