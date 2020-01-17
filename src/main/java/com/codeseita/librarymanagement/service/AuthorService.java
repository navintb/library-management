package com.codeseita.librarymanagement.service;

import com.codeseita.librarymanagement.entity.Author;
import com.codeseita.librarymanagement.form.AuthorForm;
import org.springframework.data.domain.Page;

public interface AuthorService {

    Page<Author> list(Integer page, Integer limit);

    Author add(AuthorForm form);

    Author get(Integer id);

    Author update(Integer id, AuthorForm form);
}
