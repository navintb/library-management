package com.codeseita.librarymanagement.service.impl;

import com.codeseita.librarymanagement.entity.Subscription;
import com.codeseita.librarymanagement.exception.NotFoundException;
import com.codeseita.librarymanagement.form.SubscriptionForm;
import com.codeseita.librarymanagement.repository.SubscriptionRepository;
import com.codeseita.librarymanagement.service.SubscriptionService;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> listSubscriptions() {
        return subscriptionRepository.findByStatus(Status.ACTIVE);
    }

    @Override
    public Subscription save(SubscriptionForm form) {
        return subscriptionRepository.save(new Subscription(form));
    }

    @Override
    public Subscription get(Integer id) {
        return subscriptionRepository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Subscription not found"));
    }
}
