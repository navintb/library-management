package com.codeseita.librarymanagement.repository;

import com.codeseita.librarymanagement.entity.Book;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer>, PagingAndSortingRepository<Book, Integer> {

    Page<Book> findByStatus(Status status, Pageable pageable);

    Optional<Book> findByIdAndStatus(Integer id, Status status);
}
