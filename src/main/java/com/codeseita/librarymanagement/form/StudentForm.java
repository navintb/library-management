package com.codeseita.librarymanagement.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class StudentForm {

    @NotBlank
    private String name;
    @NotNull
    private Integer subscriptionId;
    @NotNull
    private Date expiryDate;
}
