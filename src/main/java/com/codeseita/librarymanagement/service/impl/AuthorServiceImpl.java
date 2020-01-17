package com.codeseita.librarymanagement.service.impl;

import com.codeseita.librarymanagement.entity.Author;
import com.codeseita.librarymanagement.exception.NotFoundException;
import com.codeseita.librarymanagement.form.AuthorForm;
import com.codeseita.librarymanagement.repository.AuthorRepository;
import com.codeseita.librarymanagement.service.AuthorService;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Page<Author> list(Integer page, Integer limit) {
        page = (page == null || page < 0) ? 0 : page;
        limit = (limit == null || limit < 0) ? 10 : limit;
        return this.authorRepository.findByStatus(Status.ACTIVE, PageRequest.of(page, limit));
    }

    @Override
    public Author add(AuthorForm form) {
        return this.authorRepository.save(new Author(form));
    }

    @Override
    public Author get(Integer id) {
        return this.authorRepository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Author update(Integer id, AuthorForm form) {
        return this.authorRepository.save(get(id).update(form));
    }
}
