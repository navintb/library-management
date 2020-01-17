package com.codeseita.librarymanagement.controller;

import com.codeseita.librarymanagement.entity.Student;
import com.codeseita.librarymanagement.form.StudentForm;
import com.codeseita.librarymanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Page<Student> list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit) {
        return studentService.list(page, limit);
    }

    @PostMapping
    public Student add(@RequestBody @Valid StudentForm form) {
        return studentService.save(form);
    }


}
