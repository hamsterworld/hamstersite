package com.example.demo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class JoinUserForm {

    private Long UserNumber;

    @Length(min = 4,max = 12)
    private String UserId;

    @Length(min=4,max=12)
    private String UserPassWord;

    @Length(min=4,max=12)
    private String ConfirmedUserPassWord;

    @Length(min=2,max=3)
    private String UserName;

    @Length(min=4,max=6)
    private String NickName;


}
