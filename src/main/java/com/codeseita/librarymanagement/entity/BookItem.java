package com.codeseita.librarymanagement.entity;

import com.codeseita.librarymanagement.type.Status;
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
public class BookItem {
    @Id @GeneratedValue
    private int id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Student student;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    public BookItem(Book book) {
        this.book = book;
        this.status = Status.ACTIVE;
    }

}
