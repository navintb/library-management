package com.codeseita.librarymanagement.repository;

import com.codeseita.librarymanagement.entity.Student;
import com.codeseita.librarymanagement.entity.Subscription;
import com.codeseita.librarymanagement.type.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void findByStatus_ifActiveStudentExists_thenReturnActiceStudent(){
        Subscription subscription = getMockSubscription();
        this.testEntityManager.persist(subscription);
        Student student1 = getMockStudent();
        student1.setSubscription(subscription);
        this.testEntityManager.persistAndFlush(student1);
        Student student2 = getMockStudent();
        student2.setStatus(Status.DELETED);
        student2.setSubscription(subscription);
        this.testEntityManager.persistAndFlush(student2);
        Page<Student> results = this.studentRepository.findByStatus(Status.ACTIVE, null);
        Assert.assertEquals(results.getTotalElements(), 1);
        Assert.assertEquals(results.getContent().get(0).getStatus(),Status.ACTIVE);
    }

    private Subscription getMockSubscription(){
        Subscription subscription = new Subscription();
        subscription.setName("GOLD");
        subscription.setStatus(Status.ACTIVE);
        subscription.setDurationLimit(30);
        subscription.setBookLimit(2);
        return subscription;
    }

    private Student getMockStudent(){
        Student student = new Student();
        student.setName("Navin");
        student.setExpiryDate(new Date());
        student.setStatus(Status.ACTIVE);
        return student;
    }
}
