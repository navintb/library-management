package com.codeseita.librarymanagement.repository;

import com.codeseita.librarymanagement.entity.Transaction;
import com.codeseita.librarymanagement.type.TransactionStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findByStatusAndStudentId(TransactionStatus status, Integer studentId);

    Optional<Transaction> findByStatusAndStudentIdAndBookItemId(TransactionStatus status, Integer studentId, Integer bookItemId);
}
