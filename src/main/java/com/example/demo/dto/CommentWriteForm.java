package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentWriteForm {

    private Integer boardnumber;
    private String commentcontent;
    private Integer usernumber;
    private String nickname;

}
