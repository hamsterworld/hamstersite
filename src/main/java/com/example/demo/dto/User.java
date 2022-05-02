package com.example.demo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
public class User {

    @NotBlank
    private Long UserNumber;
    @Length(min = 4,max = 12)
    private String UserId;
    @Length(min=4,max=12)
    private String UserPassWord;
    @Length(min=2,max=3)
    private String UserName;
    @Length(min=4,max=6)
    private String NickName;

}
