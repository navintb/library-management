package com.codeseita.librarymanagement.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookForm {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Integer authorId;
}
