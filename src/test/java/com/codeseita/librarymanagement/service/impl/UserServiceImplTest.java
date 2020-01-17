package com.codeseita.librarymanagement.service.impl;

import com.codeseita.librarymanagement.entity.User;
import com.codeseita.librarymanagement.repository.UserRepository;
import com.codeseita.librarymanagement.service.UserService;
import com.codeseita.librarymanagement.type.Role;
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
import java.util.List;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @TestConfiguration
    static class ContextConfig{

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }

    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void list_ifUserExists_thenReturnUsers() {
        User user = getMockUser();
        List<User> users = new ArrayList<>();
        users.add(user);
        Mockito.when(this.userRepository.findByStatusAndRole(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(users);
        List<User> result = this.userService.list(Role.ADMIN);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1 );
        Assert.assertEquals(result.get(0).getId(), user.getId());
        Assert.assertEquals(result.get(0).getRole(), user.getRole());
        Assert.assertEquals(result.get(0).getStatus(), user.getStatus());
        Assert.assertEquals(result.get(0).getUsername(), user.getUsername());
    }

    private User getMockUser() {
        User user = new User();
        user.setId(1);
        user.setRole(Role.ADMIN);
        user.setStatus(Status.ACTIVE);
        user.setUsername("navin");
        return user;
    }
}
