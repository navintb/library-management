package com.codeseita.librarymanagement.controller;

import com.codeseita.librarymanagement.entity.Author;
import com.codeseita.librarymanagement.form.AuthorForm;
import com.codeseita.librarymanagement.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<Author> list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit) {
        return this.authorService.list(page, limit);
    }

    @PostMapping
    public Author post(@RequestBody @Valid AuthorForm form) {
        return this.authorService.add(form);
    }

    @GetMapping("/{id}")
    public Author get(@PathVariable("id") Integer id) {
        return this.authorService.get(id);
    }

    @PutMapping("/{id}")
    public Author put(@PathVariable("id") Integer id , @RequestBody @Valid AuthorForm form) {
        return this.authorService.update(id, form);
    }

}
