package com.codeseita.librarymanagement.service.impl;

import com.codeseita.librarymanagement.entity.Book;
import com.codeseita.librarymanagement.exception.NotFoundException;
import com.codeseita.librarymanagement.form.BookForm;
import com.codeseita.librarymanagement.repository.BookRepository;
import com.codeseita.librarymanagement.service.AuthorService;
import com.codeseita.librarymanagement.service.BookService;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    public Page<Book> list(Integer page, Integer limit) {
        page = (page == null || page < 0) ? 0 : page;
        limit = (limit == null || limit < 0) ? 10 : limit;
        return this.bookRepository.findByStatus(Status.ACTIVE, PageRequest.of(page, limit));
    }

    @Override
    public Book add(BookForm form) {
        return this.bookRepository.save(new Book(form).withAuthor(this.authorService.get(form.getAuthorId())));
    }

    @Override
    public Book get(Integer bookId) {
        return this.bookRepository.findByIdAndStatus(bookId, Status.ACTIVE)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Book update(Integer bookId, BookForm form) {
        return this.bookRepository.save(get(bookId).update(form).withAuthor(this.authorService.get(form.getAuthorId())));
    }

}
