package com.codeseita.librarymanagement.repository;

import com.codeseita.librarymanagement.entity.Subscription;
import com.codeseita.librarymanagement.type.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SubscriptionRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    public void findByStatus_ifActiveSubscriptionExists_returnActiveSubscription() {
        Subscription subscription = getDummySubscription();
        this.testEntityManager.persistAndFlush(subscription);
        List<Subscription> subscriptions = this.subscriptionRepository.findByStatus(Status.ACTIVE);
        Assert.assertEquals(subscriptions.size(), 1);
        Assert.assertEquals(subscriptions.get(0).getName(), subscription.getName());
        Assert.assertEquals(subscriptions.get(0).getBookLimit(), subscription.getBookLimit());
        Assert.assertEquals(subscriptions.get(0).getDurationLimit(), subscription.getDurationLimit());
        Assert.assertEquals(subscriptions.get(0).getStatus(), subscription.getStatus());
        Assert.assertNotNull(subscriptions.get(0).getCreateDate());
        Assert.assertNotNull(subscriptions.get(0).getUpdateDate());
    }

    @Test
    public void findByIdAndStatus_ifActiveSubscriptionExists_returnActiveSubscription() {
        Subscription subscription = this.testEntityManager.persistAndFlush(getDummySubscription());
        Optional<Subscription> optionalSubscription = this.subscriptionRepository.findByIdAndStatus(subscription.getId(), Status.ACTIVE);
        Assert.assertTrue(optionalSubscription.isPresent());
        Assert.assertEquals(optionalSubscription.get().getName(), subscription.getName());
        Assert.assertEquals(optionalSubscription.get().getBookLimit(), subscription.getBookLimit());
        Assert.assertEquals(optionalSubscription.get().getDurationLimit(), subscription.getDurationLimit());
        Assert.assertEquals(optionalSubscription.get().getStatus(), subscription.getStatus());
        Assert.assertNotNull(optionalSubscription.get().getCreateDate());
        Assert.assertNotNull(optionalSubscription.get().getUpdateDate());
    }

    private Subscription getDummySubscription(){
        Subscription subscription = new Subscription();
        subscription.setName("GOLD");
        subscription.setBookLimit(2);
        subscription.setDurationLimit(30);
        subscription.setStatus(Status.ACTIVE);
        return subscription;
    }
}
