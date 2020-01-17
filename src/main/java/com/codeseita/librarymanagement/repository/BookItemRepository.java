package com.codeseita.librarymanagement.repository;

import com.codeseita.librarymanagement.entity.BookItem;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface BookItemRepository extends CrudRepository<BookItem, Integer>, PagingAndSortingRepository<BookItem, Integer> {

    Page<BookItem> findByStatus(Status status, Pageable pageable);

    Optional<BookItem> findByIdAndStatus(Integer id, Status status);
}
