package com.codeseita.librarymanagement.service;

import com.codeseita.librarymanagement.entity.BookItem;
import com.codeseita.librarymanagement.form.BookItemForm;
import org.springframework.data.domain.Page;

public interface BookItemService {

    Page<BookItem> list(Integer page, Integer limit);

    BookItem add(BookItemForm form);

    BookItem save(BookItem bookItem);

    BookItem get(Integer id);
}
