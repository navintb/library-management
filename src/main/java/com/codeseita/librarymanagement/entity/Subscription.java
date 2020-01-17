package com.codeseita.librarymanagement.entity;

import com.codeseita.librarymanagement.form.SubscriptionForm;
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
public class Subscription {
    @Id @GeneratedValue
    private int id;
    private String name;
    private int bookLimit;
    private int durationLimit;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    public Subscription(SubscriptionForm form) {
        this.name = form.getName();
        this.bookLimit = form.getBookLimit();
        this.durationLimit = form.getDurationLimit();
        this.status = Status.ACTIVE;
    }
}
