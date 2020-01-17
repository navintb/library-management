package com.codeseita.librarymanagement.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SubscriptionForm {
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private Integer bookLimit;
    @NotNull
    @Min(1)
    private Integer durationLimit;
}
