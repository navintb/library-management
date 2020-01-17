package com.codeseita.librarymanagement.service.impl;

import com.codeseita.librarymanagement.entity.Student;
import com.codeseita.librarymanagement.exception.NotFoundException;
import com.codeseita.librarymanagement.form.StudentForm;
import com.codeseita.librarymanagement.repository.StudentRepository;
import com.codeseita.librarymanagement.service.StudentService;
import com.codeseita.librarymanagement.service.SubscriptionService;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public Page<Student> list(Integer page, Integer limit) {
        page = (page == null || page < 0) ? 0 : page;
        limit = (limit == null || limit < 0) ? 10 : limit;
        return studentRepository.findByStatus(Status.ACTIVE, PageRequest.of(page, limit, Sort.by("name")));
    }

    @Override
    public Student save(StudentForm form) {
        return studentRepository.save(new Student(form).withSubscription(subscriptionService.get(form.getSubscriptionId())));
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student get(Integer id) {
        return this.studentRepository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(NotFoundException::new);
    }


}
