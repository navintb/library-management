package com.codeseita.librarymanagement.repository;

import com.codeseita.librarymanagement.entity.Student;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface StudentRepository extends CrudRepository<Student, Integer>, PagingAndSortingRepository<Student, Integer> {

    Page<Student> findByStatus(Status status, Pageable pageable);

    Optional<Student> findByIdAndStatus(Integer id, Status status);
}
