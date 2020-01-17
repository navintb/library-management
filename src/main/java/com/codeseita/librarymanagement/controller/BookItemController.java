package com.codeseita.librarymanagement.controller;

import com.codeseita.librarymanagement.entity.BookItem;
import com.codeseita.librarymanagement.form.BookItemForm;
import com.codeseita.librarymanagement.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book-items")
public class BookItemController {

    @Autowired
    private BookItemService bookItemService;

    @GetMapping
    public Page<BookItem> list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit) {
        return this.bookItemService.list(page, limit);
    }

    @PostMapping
    public BookItem post(@RequestBody @Valid BookItemForm form) {
        return this.bookItemService.add(form);
    }

}
