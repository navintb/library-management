package com.codeseita.librarymanagement.entity;

import com.codeseita.librarymanagement.form.BookForm;
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
public class Book {
    @Id @GeneratedValue
    private int id;
    private String name;
    private String description;
    @ManyToOne
    private Author author;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    public Book(BookForm form) {
        this.name = form.getName();
        this.description = form.getDescription();
        this.status = Status.ACTIVE;
    }

    public Book withAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Book update(BookForm form) {
        this.name = form.getName();
        this.description = form.getDescription();
        return this;
    }
}
