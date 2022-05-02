package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginUserForm {

    @NotBlank
    private String UserId;

    @NotBlank
    private String UserPassWord;

}
