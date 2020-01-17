package com.codeseita.librarymanagement.service.impl;

import com.codeseita.librarymanagement.entity.BookItem;
import com.codeseita.librarymanagement.entity.Student;
import com.codeseita.librarymanagement.entity.Transaction;
import com.codeseita.librarymanagement.exception.ConflictException;
import com.codeseita.librarymanagement.exception.NotFoundException;
import com.codeseita.librarymanagement.form.TransactionForm;
import com.codeseita.librarymanagement.repository.TransactionRepository;
import com.codeseita.librarymanagement.service.BookItemService;
import com.codeseita.librarymanagement.service.StudentService;
import com.codeseita.librarymanagement.service.TransactionService;
import com.codeseita.librarymanagement.type.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private BookItemService bookItemService;

    @Override
    public List<Transaction> list(Integer studentId) {
        return this.transactionRepository.findByStatusAndStudentId(TransactionStatus.CHECK_OUT, studentId);
    }

    @Override
    public Transaction add(Integer studentId, TransactionForm form) {
        Student student = this.studentService.get(studentId);
        if(student.getBookInHand() >= student.getSubscription().getBookLimit()) {
            throw new ConflictException("Book limit reached");
        }
        BookItem bookItem = this.bookItemService.get(form.getBookItemId());
        if(bookItem.getStudent() != null) {
            throw new ConflictException("book already in checkout status");
        }
        student.setBookInHand(student.getBookInHand() + 1);
        student = this.studentService.save(student);
        bookItem.setStudent(student);
        bookItem = this.bookItemService.save(bookItem);
        return this.transactionRepository.save(new Transaction(student, bookItem));
    }

    @Override
    public Transaction returnBook(Integer studentId, Integer bookItemId) {
        Transaction transaction = this.transactionRepository.findByStatusAndStudentIdAndBookItemId(TransactionStatus.CHECK_OUT, studentId, bookItemId)
                .orElseThrow(NotFoundException::new);
        Student student = this.studentService.get(studentId);
        student.setBookInHand(student.getBookInHand() - 1);
        this.studentService.save(student);
        BookItem bookItem = this.bookItemService.get(bookItemId);
        bookItem.setStudent(null);
        this.bookItemService.save(bookItem);
        transaction.setStatus(TransactionStatus.CHECK_IN);
        return this.transactionRepository.save(transaction);
    }
}
