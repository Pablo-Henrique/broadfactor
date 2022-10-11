package com.broadfactor.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailDto {

    @NotBlank
    private String ownerRef;

    @Email
    @NotBlank
    private String emailFrom;

    @Email
    @NotBlank
    private String emailTo;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;
}
