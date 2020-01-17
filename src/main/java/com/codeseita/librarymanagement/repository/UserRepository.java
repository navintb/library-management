package com.codeseita.librarymanagement.repository;

import com.codeseita.librarymanagement.entity.User;
import com.codeseita.librarymanagement.type.Role;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByStatusAndRole(Status status, Role role);

    Optional<User> findByStatusAndUsername(Status status, String username);
}
