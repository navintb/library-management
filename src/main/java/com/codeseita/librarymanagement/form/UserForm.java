package com.codeseita.librarymanagement.form;

import com.codeseita.librarymanagement.type.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserForm {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private Role role;
}
