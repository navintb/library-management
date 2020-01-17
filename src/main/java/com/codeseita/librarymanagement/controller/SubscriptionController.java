package com.codeseita.librarymanagement.controller;

import com.codeseita.librarymanagement.entity.Subscription;
import com.codeseita.librarymanagement.form.SubscriptionForm;
import com.codeseita.librarymanagement.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public List<Subscription> list(){
        return subscriptionService.listSubscriptions();
    }

    @PostMapping
    public Subscription add(@RequestBody @Valid SubscriptionForm form){
        return subscriptionService.save(form);
    }
}
