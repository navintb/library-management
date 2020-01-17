package com.codeseita.librarymanagement.service.impl;

import com.codeseita.librarymanagement.entity.User;
import com.codeseita.librarymanagement.exception.ConflictException;
import com.codeseita.librarymanagement.form.UserForm;
import com.codeseita.librarymanagement.repository.UserRepository;
import com.codeseita.librarymanagement.service.UserService;
import com.codeseita.librarymanagement.type.Role;
import com.codeseita.librarymanagement.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> list(Role role) {
        return userRepository.findByStatusAndRole(Status.ACTIVE, role);
    }

    @Override
    public User add(UserForm form) {
        if(this.userRepository.findByStatusAndUsername(Status.ACTIVE, form.getUsername()).isPresent()) {
            throw new ConflictException("username already exists");
        }
        User user = new User();
        user.setRole(form.getRole());
        user.setUsername(form.getUsername());
//        user.setPassword(this.passwordEncoder.encode(form.getPassword()));
        user.setStatus(Status.ACTIVE);
        return this.userRepository.save(user);
    }

}
