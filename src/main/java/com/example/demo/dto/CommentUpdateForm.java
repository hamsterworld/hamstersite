package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentUpdateForm {

    private Integer commentnumber;
    private String commentcontent;
    private Integer usernumber;
    private String nickname;

}
