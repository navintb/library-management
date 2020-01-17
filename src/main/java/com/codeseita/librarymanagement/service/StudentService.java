package com.codeseita.librarymanagement.service;

import com.codeseita.librarymanagement.entity.Student;
import com.codeseita.librarymanagement.form.StudentForm;
import org.springframework.data.domain.Page;

public interface StudentService {

    public Page<Student> list(Integer page, Integer limit);

    public Student save(StudentForm form);

    public Student save(Student student);

    public Student get(Integer id);

}
