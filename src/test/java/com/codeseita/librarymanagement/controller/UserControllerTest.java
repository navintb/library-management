package com.codeseita.librarymanagement.controller;

import com.codeseita.librarymanagement.entity.User;
import com.codeseita.librarymanagement.service.UserService;
import com.codeseita.librarymanagement.type.Role;
import com.codeseita.librarymanagement.type.Status;
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
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void list_ifSuccess_returnUsers() throws Exception{
        User mockUser = getMockUser();
        List<User> users = new ArrayList<>();
        users.add(mockUser);
        Mockito.when(userService.list(ArgumentMatchers.any())).thenReturn(users);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/users?role=ADMIN")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value(mockUser.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(mockUser.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].role").value(mockUser.getRole().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value(mockUser.getStatus().name()));
    }

    private User getMockUser(){
        User user = new User();
        user.setId(1);
        user.setUsername("navin");
        user.setRole(Role.ADMIN);
        user.setStatus(Status.ACTIVE);
        return user;
    }

}
