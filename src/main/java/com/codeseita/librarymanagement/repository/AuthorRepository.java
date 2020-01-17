package com.codeseita.librarymanagement.repository;

import com.codeseita.librarymanagement.entity.Author;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Integer>, PagingAndSortingRepository<Author, Integer> {

    Page<Author> findByStatus(Status status, Pageable pageable);

    Page<Author> findByStatusAndNameLike(Status status, String name, Pageable pageable);

    Optional<Author> findByIdAndStatus(Integer id, Status status);
}
