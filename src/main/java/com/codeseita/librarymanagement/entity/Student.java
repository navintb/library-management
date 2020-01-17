package com.codeseita.librarymanagement.entity;

import com.codeseita.librarymanagement.form.StudentForm;
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
public class Student {
    @Id @GeneratedValue
    private int id;
    private String name;
    @ManyToOne
    private Subscription subscription;
    private Date expiryDate;
    private Integer bookInHand;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    public Student(StudentForm form) {
        this.name = form.getName();
        this.expiryDate = form.getExpiryDate();
        this.status = Status.ACTIVE;
    }

    public Student withSubscription(Subscription subscription){
        this.subscription = subscription;
        return this;
    }
}
