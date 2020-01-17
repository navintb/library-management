package com.codeseita.librarymanagement.controller;

import com.codeseita.librarymanagement.entity.Book;
import com.codeseita.librarymanagement.form.BookForm;
import com.codeseita.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<Book> list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit) {
        return this.bookService.list(page, limit);
    }

    @PostMapping
    public Book post(@RequestBody @Valid BookForm form) {
        return this.bookService.add(form);
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable("id") Integer id) {
        return this.bookService.get(id);
    }

    @PutMapping("{id}")
    public Book put(@PathVariable("id") Integer id, @RequestBody @Valid BookForm form) {
        return this.bookService.update(id, form);
    }

}
