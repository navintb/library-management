package com.codeseita.librarymanagement.service;

import com.codeseita.librarymanagement.entity.Subscription;
import com.codeseita.librarymanagement.form.SubscriptionForm;

import java.util.List;

public interface SubscriptionService {

    public List<Subscription> listSubscriptions();

    public Subscription save(SubscriptionForm form);

    public Subscription get(Integer id);
}
