package com.codeseita.librarymanagement.service.impl;

import com.codeseita.librarymanagement.entity.BookItem;
import com.codeseita.librarymanagement.exception.NotFoundException;
import com.codeseita.librarymanagement.form.BookItemForm;
import com.codeseita.librarymanagement.repository.BookItemRepository;
import com.codeseita.librarymanagement.service.BookItemService;
import com.codeseita.librarymanagement.service.BookService;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookItemServiceImpl implements BookItemService {

    @Autowired
    private BookItemRepository bookItemRepository;

    @Autowired
    private BookService bookService;

    @Override
    public Page<BookItem> list(Integer page, Integer limit) {
        page = (page == null || page < 0) ? 0 : page;
        limit = (limit == null || limit < 0) ? 10 : limit;
        return this.bookItemRepository.findByStatus(Status.ACTIVE, PageRequest.of(page, limit));
    }

    @Override
    public BookItem add(BookItemForm form) {
        return this.bookItemRepository.save(new BookItem(this.bookService.get(form.getBookId())));
    }

    @Override
    public BookItem save(BookItem bookItem) {
        return this.bookItemRepository.save(bookItem);
    }

    @Override
    public BookItem get(Integer id) {
        return this.bookItemRepository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(NotFoundException::new);
    }
}
