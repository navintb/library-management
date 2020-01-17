package com.codeseita.librarymanagement.entity;

import com.codeseita.librarymanagement.form.AuthorForm;
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
public class Author {
    @Id @GeneratedValue
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    public Author(AuthorForm form) {
        this.name = form.getName();
        this.status = Status.ACTIVE;
    }

    public Author update(AuthorForm form) {
        this.name = form.getName();
        return this;
    }

}
