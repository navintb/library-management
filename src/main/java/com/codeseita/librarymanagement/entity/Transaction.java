package com.codeseita.librarymanagement.entity;

import com.codeseita.librarymanagement.type.TransactionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id @GeneratedValue
    private int id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private BookItem bookItem;
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    public Transaction(Student student, BookItem bookItem) {
        this.student = student;
        this.bookItem = bookItem;
        this.status = TransactionStatus.CHECK_OUT;
    }
}
