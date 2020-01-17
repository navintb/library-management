package com.codeseita.librarymanagement.controller;

import com.codeseita.librarymanagement.entity.Transaction;
import com.codeseita.librarymanagement.form.TransactionForm;
import com.codeseita.librarymanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students/{studentId}/book-items")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> list(@PathVariable("studentId") Integer studentId) {
        return this.transactionService.list(studentId);
    }

    @PostMapping
    public Transaction post(@PathVariable("studentId") Integer studentId, @RequestBody @Valid TransactionForm form) {
        return this.transactionService.add(studentId, form);
    }

    @DeleteMapping("/{bookItemId}")
    public Transaction delete(@PathVariable("studentId") Integer studentId, @PathVariable("bookItemId") Integer bookItemId) {
        return this.transactionService.returnBook(studentId, bookItemId);
    }
}
