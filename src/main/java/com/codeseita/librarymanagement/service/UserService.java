package com.codeseita.librarymanagement.service;

import com.codeseita.librarymanagement.entity.User;
import com.codeseita.librarymanagement.form.UserForm;
import com.codeseita.librarymanagement.type.Role;

import java.util.List;

public interface UserService {

    List<User> list(Role role);

    User add(UserForm form);

}
