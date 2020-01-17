package com.codeseita.librarymanagement.controller;

import com.codeseita.librarymanagement.entity.Subscription;
import com.codeseita.librarymanagement.form.SubscriptionForm;
import com.codeseita.librarymanagement.service.SubscriptionService;
import com.codeseita.librarymanagement.type.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriptionService subscriptionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void list_ifSuccess_ReturnsSubscriptions() throws Exception {
        Subscription dummySubscription = getDummySubscription();
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(dummySubscription);
        Mockito.when(subscriptionService.listSubscriptions()).thenReturn(subscriptions);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(dummySubscription.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(dummySubscription.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookLimit").value(dummySubscription.getBookLimit()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].durationLimit").value(dummySubscription.getDurationLimit()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value(dummySubscription.getStatus().name()));
    }

    @Test
    public void add_ifSuccess_ReturnsSubscription() throws Exception {
        SubscriptionForm form = getSubscriptionForm();
        Subscription dummySubscription = getDummySubscription();
        Mockito.when(subscriptionService.save(ArgumentMatchers.any())).thenReturn(dummySubscription);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(dummySubscription.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(dummySubscription.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookLimit").value(dummySubscription.getBookLimit()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.durationLimit").value(dummySubscription.getDurationLimit()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(dummySubscription.getStatus().name()));
    }

    @Test
    public void add_ifNameIsNull_thenReturnsWithStatusBadRequest() throws Exception {
        SubscriptionForm form = getSubscriptionForm();
        form.setName(null);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void add_ifBookLimitIsNull_thenReturnsWithStatusBadRequest() throws Exception {
        SubscriptionForm form = getSubscriptionForm();
        form.setBookLimit(null);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void add_ifBookLimitIsLessThan1_thenReturnsWithStatusBadRequest() throws Exception {
        SubscriptionForm form = getSubscriptionForm();
        form.setBookLimit(0);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void add_ifDurationLimitIsNull_thenReturnsWithStatusBadRequest() throws Exception {
        SubscriptionForm form = getSubscriptionForm();
        form.setDurationLimit(null);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void add_ifDurationLimitIsLessThan1_thenReturnsWithStatusBadRequest() throws Exception {
        SubscriptionForm form = getSubscriptionForm();
        form.setDurationLimit(0);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private SubscriptionForm getSubscriptionForm(){
        SubscriptionForm form = new SubscriptionForm();
        form.setName("GOLD");
        form.setBookLimit(1);
        form.setDurationLimit(30);
        return form;
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
}
