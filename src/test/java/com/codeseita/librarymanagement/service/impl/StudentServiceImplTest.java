package com.codeseita.librarymanagement.service.impl;

import com.codeseita.librarymanagement.entity.Student;
import com.codeseita.librarymanagement.entity.Subscription;
import com.codeseita.librarymanagement.form.StudentForm;
import com.codeseita.librarymanagement.repository.StudentRepository;
import com.codeseita.librarymanagement.service.StudentService;
import com.codeseita.librarymanagement.service.SubscriptionService;
import com.codeseita.librarymanagement.type.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
public class StudentServiceImplTest {

    @TestConfiguration
    static class contextConfig{

        @Bean
        public StudentService studentService() {
            return new StudentServiceImpl();
        }
    }

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private SubscriptionService subscriptionService;

    @Test
    public void list_ifActiveStudentExists_returnStudents() {
        Student mockStudent = getMockStudent();
        List<Student> students = new ArrayList<>();
        students.add(mockStudent);
        Mockito.when(studentRepository.findByStatus(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(new PageImpl<>(students));
        Page<Student> page = studentService.list(null, null);
        Assert.assertTrue(page.hasContent());
        Assert.assertEquals(page.getContent().get(0).getId(), mockStudent.getId());
        Assert.assertEquals(page.getContent().get(0).getName(), mockStudent.getName());
        Assert.assertEquals(page.getContent().get(0).getExpiryDate(), mockStudent.getExpiryDate());
        Assert.assertEquals(page.getContent().get(0).getStatus(), mockStudent.getStatus());
        Assert.assertEquals(page.getContent().get(0).getSubscription().getName(), mockStudent.getSubscription().getName());
        Assert.assertEquals(page.getContent().get(0).getSubscription().getStatus(), mockStudent.getSubscription().getStatus());
        Assert.assertEquals(page.getContent().get(0).getSubscription().getDurationLimit(), mockStudent.getSubscription().getDurationLimit());
        Assert.assertEquals(page.getContent().get(0).getSubscription().getBookLimit(), mockStudent.getSubscription().getBookLimit());
    }

    @Test
    public void save_ifSuccess_returnSavedStudent(){
        StudentForm form = getStudentForm();
        Student mockStudent = getMockStudent();
        Mockito.when(this.studentRepository.save(ArgumentMatchers.any())).thenReturn(mockStudent);
        Mockito.when(this.subscriptionService.get(ArgumentMatchers.any())).thenReturn(mockStudent.getSubscription());
        Student result = this.studentService.save(form);
        Assert.assertEquals(result.getId(), mockStudent.getId());
        Assert.assertEquals(result.getName(), mockStudent.getName());
        Assert.assertEquals(result.getExpiryDate(), mockStudent.getExpiryDate());
        Assert.assertEquals(result.getStatus(), mockStudent.getStatus());
        Assert.assertEquals(result.getSubscription().getName(), mockStudent.getSubscription().getName());
        Assert.assertEquals(result.getSubscription().getStatus(), mockStudent.getSubscription().getStatus());
        Assert.assertEquals(result.getSubscription().getDurationLimit(), mockStudent.getSubscription().getDurationLimit());
        Assert.assertEquals(result.getSubscription().getBookLimit(), mockStudent.getSubscription().getBookLimit());
    }

    private StudentForm getStudentForm(){
        StudentForm form = new StudentForm();
        form.setName("Navin");
        form.setSubscriptionId(1);
        form.setExpiryDate(new Date());
        return form;
    }

    private Student getMockStudent(){
        Subscription subscription = new Subscription();
        subscription.setName("GOLD");
        subscription.setStatus(Status.ACTIVE);
        subscription.setDurationLimit(30);
        subscription.setBookLimit(2);
        Student student = new Student();
        student.setId(1);
        student.setName("Navin");
        student.setExpiryDate(new Date());
        student.setStatus(Status.ACTIVE);
        student.setSubscription(subscription);
        return student;
    }
}
