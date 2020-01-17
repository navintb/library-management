package com.codeseita.librarymanagement.service.impl;

import com.codeseita.librarymanagement.entity.Subscription;
import com.codeseita.librarymanagement.exception.NotFoundException;
import com.codeseita.librarymanagement.form.SubscriptionForm;
import com.codeseita.librarymanagement.repository.SubscriptionRepository;
import com.codeseita.librarymanagement.service.SubscriptionService;
import com.codeseita.librarymanagement.type.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class SubscriptionServiceTest {

    @TestConfiguration
    static class ContextConfig{

        @Bean
        public SubscriptionService subscriptionService(){
            return new SubscriptionServiceImpl();
        }
    }

    @Autowired
    private SubscriptionService subscriptionService;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @Test
    public void listSubscriptions_ifSubscriptionsExists_returnSubscriptions(){
        Subscription subscription = getDummySubscription();
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(subscription);
        Mockito.when(subscriptionRepository.findByStatus(Status.ACTIVE)).thenReturn(subscriptions);
        List<Subscription> result = subscriptionService.listSubscriptions();
        Assert.assertEquals(result.get(0).getId(), subscription.getId());
        Assert.assertEquals(result.get(0).getName(), subscription.getName());
        Assert.assertEquals(result.get(0).getBookLimit(), subscription.getBookLimit());
        Assert.assertEquals(result.get(0).getDurationLimit(), subscription.getDurationLimit());
        Assert.assertEquals(result.get(0).getStatus(), subscription.getStatus());
        Assert.assertEquals(result.get(0).getCreateDate(), subscription.getCreateDate());
        Assert.assertEquals(result.get(0).getUpdateDate(), subscription.getUpdateDate());
    }

    @Test
    public void save_ifSuccess_returnSubscription(){
        Subscription subscription = getDummySubscription();
        Mockito.when(subscriptionRepository.save(ArgumentMatchers.any())).thenReturn(subscription);
        Subscription result = subscriptionService.save(getDummySubscriptionForm());
        Assert.assertEquals(result.getId(), subscription.getId());
        Assert.assertEquals(result.getName(), subscription.getName());
        Assert.assertEquals(result.getBookLimit(), subscription.getBookLimit());
        Assert.assertEquals(result.getDurationLimit(), subscription.getDurationLimit());
        Assert.assertEquals(result.getStatus(), subscription.getStatus());
        Assert.assertEquals(result.getCreateDate(), subscription.getCreateDate());
        Assert.assertEquals(result.getUpdateDate(), subscription.getUpdateDate());
    }

    @Test
    public void get_ifSuccess_returnSubscription(){
        Subscription subscription = getDummySubscription();
        Mockito.when(subscriptionRepository.findByIdAndStatus(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(Optional.of(subscription));
        Subscription result = subscriptionService.get(1);
        Assert.assertEquals(result.getId(), subscription.getId());
        Assert.assertEquals(result.getName(), subscription.getName());
        Assert.assertEquals(result.getBookLimit(), subscription.getBookLimit());
        Assert.assertEquals(result.getDurationLimit(), subscription.getDurationLimit());
        Assert.assertEquals(result.getStatus(), subscription.getStatus());
        Assert.assertEquals(result.getCreateDate(), subscription.getCreateDate());
        Assert.assertEquals(result.getUpdateDate(), subscription.getUpdateDate());
    }

    @Test(expected = NotFoundException.class)
    public void get_ifNotExists_throwsNotFoundException(){
        Mockito.when(subscriptionRepository.findByIdAndStatus(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(Optional.empty());
        subscriptionService.get(1);
    }

    private Subscription getDummySubscription(){
        Subscription subscription = new Subscription();
        subscription.setId(1);
        subscription.setName("GOLD");
        subscription.setBookLimit(2);
        subscription.setDurationLimit(30);
        subscription.setStatus(Status.ACTIVE);
        subscription.setCreateDate(new Date());
        subscription.setUpdateDate(new Date());
        return subscription;
    }

    private SubscriptionForm getDummySubscriptionForm() {
        SubscriptionForm form = new SubscriptionForm();
        form.setName("GOLD");
        form.setDurationLimit(30);
        form.setBookLimit(2);
        return form;
    }
}
