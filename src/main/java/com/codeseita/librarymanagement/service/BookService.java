package com.codeseita.librarymanagement.service;

import com.codeseita.librarymanagement.entity.Book;
import com.codeseita.librarymanagement.form.BookForm;
import org.springframework.data.domain.Page;

public interface BookService {

    Page<Book> list(Integer page, Integer limit);

    Book add(BookForm form);

    Book get(Integer bookId);

    Book update(Integer bookId, BookForm form);

}
