package com.codeseita.librarymanagement.controller;

import com.codeseita.librarymanagement.entity.User;
import com.codeseita.librarymanagement.service.UserService;
import com.codeseita.librarymanagement.type.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> list(@RequestParam  Role role) {
        return userService.list(role);
    }
}
