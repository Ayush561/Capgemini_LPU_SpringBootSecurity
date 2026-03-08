package com.capgemini.libraryManagementSystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.libraryManagementSystem.entity.BorrowRecord;


public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

}
