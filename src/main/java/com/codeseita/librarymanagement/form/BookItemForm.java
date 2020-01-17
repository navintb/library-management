package com.codeseita.librarymanagement.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookItemForm {
    @NotNull
    private Integer bookId;
}
