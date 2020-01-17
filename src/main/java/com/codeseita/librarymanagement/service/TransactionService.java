package com.codeseita.librarymanagement.service;

import com.codeseita.librarymanagement.entity.Transaction;
import com.codeseita.librarymanagement.form.TransactionForm;

import java.util.List;

public interface TransactionService {

    List<Transaction> list(Integer studentId);

    Transaction add(Integer studentId, TransactionForm form);

    Transaction returnBook(Integer studentId, Integer bookItemId);
}
