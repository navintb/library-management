package com.codeseita.librarymanagement.repository;

import com.codeseita.librarymanagement.entity.Subscription;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

    public List<Subscription> findByStatus(Status status);

    public Optional<Subscription> findByIdAndStatus(Integer id, Status status);

}
