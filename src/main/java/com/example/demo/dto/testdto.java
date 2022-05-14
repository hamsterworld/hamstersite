package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class testdto {

    @NotBlank
    private String userid;

    @NotBlank
    private String userpassword;

}
