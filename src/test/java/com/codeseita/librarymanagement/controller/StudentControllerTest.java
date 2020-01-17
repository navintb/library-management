package com.codeseita.librarymanagement.controller;

import com.codeseita.librarymanagement.entity.Student;
import com.codeseita.librarymanagement.entity.Subscription;
import com.codeseita.librarymanagement.form.StudentForm;
import com.codeseita.librarymanagement.service.StudentService;
import com.codeseita.librarymanagement.type.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void list_ifSuccess_thenReturnStudents() throws Exception{
        Student student = getMockStudent();
        List<Student> students = new ArrayList<>();
        students.add(student);
        Page<Student> page  = new PageImpl<>(students);
        Mockito.when(studentService.list(null,null)).thenReturn(page);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].id").value(student.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].name").value(student.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].status").value(student.getStatus().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].subscription.name").value(student.getSubscription().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].subscription.durationLimit").value(student.getSubscription().getDurationLimit()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].subscription.bookLimit").value(student.getSubscription().getBookLimit()));
    }

    @Test
    public void add_ifSuccess_returnNewStudent() throws Exception{
        Student student = getMockStudent();
        Mockito.when(studentService.save((StudentForm) ArgumentMatchers.any())).thenReturn(student);
        StudentForm form = getStudentForm();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(student.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(student.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(student.getStatus().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("subscription.name").value(student.getSubscription().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("subscription.durationLimit").value(student.getSubscription().getDurationLimit()))
                .andExpect(MockMvcResultMatchers.jsonPath("subscription.bookLimit").value(student.getSubscription().getBookLimit()));

    }

    @Test
    public void add_ifNameIsNull_returnBadRequest() throws Exception{
        Student student = getMockStudent();
        Mockito.when(studentService.save((StudentForm) ArgumentMatchers.any())).thenReturn(student);
        StudentForm form = getStudentForm();
        form.setName(null);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void add_ifSubscriptionIdIsNull_returnBadRequest() throws Exception{
        Student student = getMockStudent();
        Mockito.when(studentService.save((StudentForm) ArgumentMatchers.any())).thenReturn(student);
        StudentForm form = getStudentForm();
        form.setSubscriptionId(null);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void add_ifExpiryDateIsNull_returnBadRequest() throws Exception{
        Student student = getMockStudent();
        Mockito.when(studentService.save((StudentForm) ArgumentMatchers.any())).thenReturn(student);
        StudentForm form = getStudentForm();
        form.setExpiryDate(null);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

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
