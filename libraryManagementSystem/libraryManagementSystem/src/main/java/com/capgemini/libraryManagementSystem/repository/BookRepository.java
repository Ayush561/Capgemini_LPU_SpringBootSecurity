package com.capgemini.libraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.libraryManagementSystem.entity.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

}
